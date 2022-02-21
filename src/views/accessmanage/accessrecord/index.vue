<template>
  <div class="hello">
    <div class="header">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-select
            v-model="queryDate.pip"
            clearable
            size="small"
            placeholder="打卡地点"
            style="width: 100%"
          >
            <el-option
              v-for="item in optionsa"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="6">
          <el-input
            clearable
            size="small"
            placeholder="人员姓名"
            v-model="queryDate.name"
          >
          </el-input>
        </el-col>
        <el-col :span="6">
          <el-input
            size="small"
            clearable
            placeholder="学号/工号"
            v-model="queryDate.card"
          >
          </el-input>
        </el-col>
        <el-col :span="6">
          <el-select
            v-model="queryDate.typeId"
            size="small"
            clearable
            placeholder="人员类型"
          >
            <el-option
              v-for="item in typeRrr"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-select
            v-model="queryDate.ctype"
            clearable
            size="small"
            placeholder="进出校"
            style="width: 100%"
          >
            <el-option
              v-for="item in optionsc"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="10">
          <el-date-picker
            clearable
            style="width: 100%"
            v-model="valuetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            size="small"
            type="datetimerange"
            range-separator="至"
            start-placeholder="创建开始日期"
            end-placeholder="创建结束日期"
          >
          </el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-button
            size="small"
            type="primary"
            icon="el-icon-search"
            @click="userSearch()"
            >搜索</el-button
          >
          <el-button size="small" type="primary" @click="handleDownload"
            >导出</el-button
          >
        </el-col>
      </el-row>
    </div>
    <div class="tableBox">
      <el-table
        :data="FindByList"
        :header-cell-style="{ background: '#eef1f6', color: '#606266' }"
        border
        width="100%"
      >
        <el-table-column label="打卡地点" prop="pip" align="center" width="150">
        </el-table-column>
        <el-table-column
          label="人员姓名"
          prop="name"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="人员类型"
          prop="typeName"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="学号/工号/身份证号"
          prop="card"
          align="center"
          width="200"
        >
        </el-table-column>
        <el-table-column
          label="联系方式"
          prop="phone"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="扫码时间"
          prop="scanCodeTime"
          align="center"
          width="200"
        >
        </el-table-column>
        <el-table-column label="进出校" prop="ctype" align="center" width="100">
        </el-table-column>
        <el-table-column label="备注" prop="info" align="center" width="200">
        </el-table-column>
      </el-table>
      <!--分页 -->
      <Pagination
        :total="total"
        :page.sync="pageQuery.pageCurrent"
        :limit.sync="pageQuery.pageSize"
        @pagination="getFindByList"
      />
    </div>
  </div>
</template>
<script>
import Pagination from "@/components//Pagination/index.vue";
import { userfindByPage, exportInfo } from "@/api/Management";
import exportFile from "@/utils/exportFile";

export default {
  name: "HelloWorld",
  components: {
    Pagination,
  },
  data() {
    return {
      typeRrr: [],
      valuetime: "",
      total: 0, //总条数
      pageQuery: {
        pageSize: 10, //当前显示多少数据
        pageCurrent: 1, // 当前页
      },
      queryDate: {
        card: "", //学号/工号/身份证号
        startTime: "", //入校起时间
        endTime: "", // 	入校止时间
        ctype: "", // 进出校
        name: "", //姓名
        pip: "", // 打卡地点
        typeId: "", //人员类型id
      },
      selectType: 0, // 0:新增 1:修改 2: 查看
      FindByList: [], //列表数据
      isAddMembers: false,
      titleShow: "新增人员信息", //
      addForm: {
        createTime: "", // 创建时间
        id: "", // 主键id
        remark: "", //备注
        name: "", //姓名
      },
      rules: {
        name: [
          { required: true, message: "请选择人员类型名称", trigger: "change" },
        ],
      },
      optionsa: [
        { label: "陕职-出口1", value: "陕职-出口1" },
        { label: "陕职-入口1", value: "陕职-入口1" },
        { label: "陕职-出口2", value: "陕职-出口2" },
        { label: "陕职-入口2", value: "陕职-入口2" },
      ],
      optionsb: [
        { label: "老师", value: "1" },
        { label: "学生", value: "2" },
        { label: "访客", value: "3" },
      ],
      optionsc: [
        { label: "出校", value: "出校" },
        { label: "入校", value: "入校" },
      ],
      options: [
        {
          value: "教职工",
          label: "教职工",
        },
        {
          value: "商铺",
          label: "商铺",
        },
        {
          value: "学生",
          label: "学生",
        },
      ],
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
      },
      fileList: [],
    };
  },
  created() {
    // this.fetchData();
    this.getFindByList();
  },
  methods: {
    // 获取数据列表
    getFindByList() {
      this.queryDate.startTime = this.valuetime[0] || "";
      this.queryDate.endTime = this.valuetime[1] || "";
      let parems = Object.assign(this.queryDate, this.pageQuery);
      userfindByPage(parems).then((res) => {
        this.FindByList = res.data.records; //列表数据
        this.total = res.data.total; //总条数
        this.pageQuery.pageCurrent = res.data.current; //当前页
        this.pageQuery.pageSize = res.data.size; //每页显示条数
      });
    },
    //搜索
    userSearch() {
      this.getFindByList();
    },
    // 记录导出
    handleDownload() {},
  },
  computed: {},
};
</script>
<style lang="scss" scoped>
// .tableBox {
// margin-top: 40px;
// }
</style>
