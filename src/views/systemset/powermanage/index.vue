<template>
  <div class="hello">
    <div class="header">
      <el-row :gutter="20">
        <el-col :span="3">
          <el-input
            size="small"
            clearable
            placeholder="权限名称"
            v-model="queryDate.name"
          >
          </el-input>
        </el-col>
        <el-col :span="3">
          <!-- 下拉框 -->
          <el-select v-model="idstate" placeholder="状态" size="small">
            <el-option
              v-for="item in options"
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
          <el-button size="small" type="primary" @click="addMembers"
            >新增</el-button
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
        <el-table-column
          label="权限名称"
          prop="total"
          align="center"
          width="200"
        >
        </el-table-column>
        <el-table-column
          label="创建日期"
          prop="createTime"
          align="center"
          width="160"
        >
        </el-table-column>
        <el-table-column label="状态" prop="idstate" align="center" width="100">
          <el-switch
            v-model="switchvalue"
            active-color="#1E90FF"
            inactive-color="#778899"
          >
          </el-switch>
        </el-table-column>
        <el-table-column label="备注" prop="remark" align="center" width="200">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="success" @click="modifyData(scope.row)"
              >编辑</el-button
            >
            <el-button size="mini" type="danger" @click="deleteData(scope.row)"
              >移除</el-button
            >
          </template>
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
    <!-- 弹出框 -->
    <el-dialog
      :title="titleShow"
      width="50%"
      center
      :before-close="addRoleCancel"
      :visible.sync="isAddMembers"
    >
      <el-form
        :model="addForm"
        :rules="rules"
        ref="roleForm"
        label-width="100px"
        size="mini"
      >
        <el-form-item label="权限名称" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="配置权限" prop="roleper">
          <el-tree
            :data="treeData"
            show-checkbox
            :props="defaultProps"
            v-model="addForm.roleper"
          >
          </el-tree>
        </el-form-item>
        <el-form-item label="是否启用" prop="enable">
          <!-- 下拉框 -->
          <el-select v-model="addForm.enable" placeholder="状态" size="small">
            <el-option
              v-for="item in optionsb"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            type="textarea"
            :rows="3"
            v-model="addForm.remark"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="addRoleCancel()">取消</el-button>
        <el-button size="mini" type="primary" @click="sumbitAddRow('roleForm')"
          >确定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>
<script>
import Pagination from "@/components//Pagination/index.vue";
import {
  getfindType,
  deletePerm,
  insertPerm,
  updatePerm,
} from "@/api/Management";
export default {
  name: "HelloWorld",
  components: {
    Pagination,
  },
  data() {
    return {
      enable: [],
      roleper: [],
      idstate: [],
      idstatea: [],
      idstateb: [],
      switchvalue: true,
      valuetime: "",
      total: 0, //总条数
      pageQuery: {
        pageSize: 10, //当前显示多少数据
        pageCurrent: 1, // 当前页
      },
      queryDate: {
        startTime: "", //分页-创建起时间
        endTime: "", // 	分页-创建止时间
        name: "", //姓名
      },
      selectType: 0, // 0:新增 1:修改 2: 查看
      FindByList: [], //列表数据
      isAddMembers: false,
      titleShow: "新增权限", //
      // 新增弹出框
      addForm: {
        createTime: "", // 创建时间
        id: "", // 主键id
        remark: "", //备注
        name: "", //姓名
      },
      rules: {
        name: [{ required: true, message: "请输入角色名称", trigger: "blur" }],
        enable: [
          { required: true, message: "请选择是否启用", trigger: "change" },
        ],
        roleper: [{ required: true, message: "请选择权限", trigger: "change" }],
      },
      options: [
        {
          label: "启用",
          value: "01",
        },
        {
          label: "禁用",
          value: "02",
        },
      ],
      optionsa: [
        {
          label: "超级权限",
          value: "0",
        },
        {
          label: "普通权限",
          value: "1",
        },
      ],
      optionsb: [
        {
          label: "启用",
          value: "2",
        },
        {
          label: "禁用",
          value: "3",
        },
      ],
      value: "",
      treeData: [
        {
          id: 1,
          label: "首页",
        },
        {
          id: 2,
          label: "人员管理",
          children: [
            {
              id: 5,
              label: "人员信息",
              children: [
                { id: 51, label: "查询" },
                { id: 52, label: "新增" },
                { id: 53, label: "批量导入" },
                { id: 54, label: "批量删除" },
                { id: 55, label: "编辑" },
                { id: 56, label: "移除" },
              ],
            },
            {
              id: 6,
              label: "人员类型管理",
              children: [
                { id: 61, label: "查询" },
                { id: 62, label: "新增" },
                { id: 63, label: "编辑" },
                { id: 64, label: "移除" },
              ],
            },
          ],
        },
        {
          id: 3,
          label: "学校名称",
          children: [
            {
              id: 7,
              label: "院系名称1",
              children: [
                { id: 71, label: "班级1" },
                { id: 72, label: "班级2" },
                { id: 73, label: "班级3" },
                { id: 74, label: "班级4" },
              ],
            },
            {
              id: 8,
              label: "院系名称2",
              children: [
                { id: 81, label: "班级1" },
                { id: 82, label: "班级2" },
                { id: 83, label: "班级3" },
                { id: 84, label: "班级4" },
              ],
            },
          ],
        },
      ],
      defaultProps: {
        children: "children",
        label: "label",
      },
    };
  },
  created() {
    this.getFindByList();
  },
  methods: {
    // 获取数据列表
    getFindByList() {
      this.queryDate.startTime = this.valuetime[0] || "";
      this.queryDate.endTime = this.valuetime[1] || "";
      let parems = Object.assign(this.queryDate, this.pageQuery);
      getfindType(parems).then((res) => {
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
    //新增按钮
    addMembers() {
      this.titleShow = "新增权限";
      this.selectType = 0;
      this.addForm = {};
      this.isAddMembers = true;
    },
    //编辑按钮
    modifyData(row) {
      this.titleShow = "修改角色";
      this.selectType = 1;
      this.isAddMembers = true;
      this.addForm = {
        // createTime: row.createTime, // 创建时间
        id: row.id, // 主键id
        remark: row.remark, //备注
        name: row.name, //姓名
      };
    },
    //关闭弹窗
    addRoleCancel() {
      this.isAddMembers = false;
    },
    //确定新增按钮
    sumbitAddRow(formName) {
      if (this.selectType == 0) {
        //新增操作
        this.$refs[formName].validate((valid) => {
          if (valid) {
            insertPerm(this.addForm).then((res) => {
              this.$notify({
                title: "添加成功",
                message: "成功提示!",
                type: "success",
              });
              this.getFindByList();
              this.isAddMembers = false;
            });
          } else {
            console.log("error submit!!");
            return false;
          }
        });
      } else if (this.selectType == 1) {
        //修改操作
        this.$refs[formName].validate((valid) => {
          if (valid) {
            updatePerm(this.addForm).then((res) => {
              this.$notify({
                title: "修改成功",
                message: "成功提示!",
                type: "success",
              });
              this.getFindByList();
              this.isAddMembers = false;
            });
          } else {
            console.log("error submit!!");
            return false;
          }
        });
      }
    },
    // 删除操作
    deleteData(row) {
      this.$confirm("确定要删除吗?, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deletePerm({ permId: row.id }).then(() => {
            this.getFindByList();
            this.$notify({
              title: "删除成功",
              message: "成功提示!",
              type: "success",
            });
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
  computed: {},
};
</script>
<style lang="scss" scoped>
.tableBox {
  margin-top: 40px;
}
</style>

