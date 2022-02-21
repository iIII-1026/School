<template>
  <div class="hello">
    <div class="header">
      <el-row :gutter="20" class="grid-content">
        <el-col :span="4">
          <el-input
            size="small"
            clearable
            placeholder="管理员姓名"
            v-model="queryDate.name"
          >
          </el-input>
        </el-col>
        <el-col :span="4">
          <!-- 下拉框 -->
          <el-select v-model="idstate" placeholder="角色筛选" size="small">
            <el-option
              v-for="item in optionsb"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="4">
          <!-- 下拉框 -->
          <el-select v-model="idstateb" placeholder="权限筛选" size="small">
            <el-option
              v-for="item in optionsa"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="4">
          <!-- 下拉框 -->
          <el-select v-model="idstatea" placeholder="状态" size="small">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="grid-content">
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
          <el-button size="small" type="primary" @click="addMembers()"
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
          label="登录账号"
          prop="idname"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="管理员姓名"
          prop="adminname"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="管理员联系方式"
          prop="phonenum"
          align="center"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="权限"
          prop="juris"
          align="center"
          width="100"
        ></el-table-column>
        <el-table-column label="角色" prop="role" align="center" width="100">
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
        <el-table-column label="操作" align="center" width="160">
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
        <el-form-item label="登录账号" prop="idname">
          <el-input v-model="addForm.idname"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
        <el-form-item label="管理员姓名" prop="adminname">
          <el-input v-model="addForm.adminname"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="phonenum">
          <el-input v-model="addForm.phonenum"></el-input>
        </el-form-item>
        <el-form-item label="系统角色" prop="idstateb">
          <!-- 下拉框 -->
          <el-select
            v-model="addForm.idstateb"
            placeholder="选择系统角色权限"
            size="small"
          >
            <el-option
              v-for="item in optionsa"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否启用" prop="idstatea">
          <!-- 下拉框 -->
          <el-select v-model="addForm.idstatea" placeholder="状态" size="small">
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
  deleteAdmin,
  insertAdmin,
  updateAdmin,
} from "@/api/Management";
export default {
  name: "HelloWorld",
  components: {
    Pagination,
  },
  data() {
    return {
      enable: [],
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
      titleShow: "新增账号", //
      // 新增弹出框
      addForm: {
        idname: "", //登录账号
        adminname: "", //管理员姓名
        phonenum: "", //管理员联系方式
        juris: "", //权限
        role: "", //角色
        createTime: "", // 创建时间
        idstate: "", //状态
        id: "", // 主键id
        remark: "", //备注
        name: "", //姓名
      },
      rules: {
        idname: [
          { required: true, message: "请输入登录账号", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入登录账密码", trigger: "blur" },
        ],
        adminname: [
          { required: true, message: "请输入管理员姓名", trigger: "blur" },
        ],
        phonenum: [
          { required: true, message: "请输入联系方式", trigger: "blur" },
        ],
        idstateb: [
          { required: true, message: "请选择系统角色", trigger: "change" },
        ],
        idstatea: [
          { required: true, message: "请选择是否启用", trigger: "change" },
        ],
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
          label: "普通人员",
          value: "0",
        },
        {
          label: "管理人员",
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
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
      },
      fileList: [],
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
      this.titleShow = "新增账号";
      this.selectType = 0;
      this.addForm = {};
      this.isAddMembers = true;
    },
    //编辑按钮
    modifyData(row) {
      this.titleShow = "修改账号";
      this.selectType = 1;
      this.isAddMembers = true;
      this.addForm = {
        idname: row.idname, //登录账号
        adminname: row.adminname, //管理员姓名
        phonenum: row.phonenum, //管理员联系方式
        // createTime: row.createTime, // 创建时间
        idstate: row.idstate, //状态
        juris: row.juris, //权限
        role: row.role, //角色
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
            insertAdmin(this.addForm).then(() => {
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
            updateAdmin(this.addForm).then((res) => {
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
          deleteAdmin({ adminId: row.id }).then(() => {
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

