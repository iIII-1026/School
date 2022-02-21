<template>
  <div class="hello">
    <el-row :gutter="20" class="grid-content">
      <el-col :span="4"
        ><div class="grid-content bg-purple">
          <el-select v-model="value" size="mini" placeholder="所属学校">
            <el-option
              v-for="item in optionsa"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select></div
      ></el-col>
      <el-col :span="4"
        ><div class="grid-content bg-purple">
          <el-input
            size="mini"
            placeholder="人员姓名"
            v-model="input"
          ></el-input></div
      ></el-col>
      <el-col :span="4"
        ><div class="grid-content bg-purple">
          <el-input
            size="mini"
            placeholder="学号/工号"
            v-model="inputs"
          ></el-input></div
      ></el-col>
      <el-col :span="4"
        ><div class="grid-content bg-purple">
          <el-select v-model="value1" size="mini" placeholder="人员类型">
            <el-option
              v-for="item in optionsb"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select></div
      ></el-col>
      <el-col :span="4"
        ><div class="grid-content bg-purple">
          <el-select v-model="value2" size="mini" placeholder="身份状态">
            <el-option
              v-for="item in optionsc"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select></div
      ></el-col>
    </el-row>
    <el-row class="table-grid-content">
      <el-col :span="13" class="grid">
        <div class="block">
          <span class="demonstration">入校起止时间: &nbsp;</span>
          <el-date-picker
            v-model="valuetime"
            value-format="yyyy-MM-dd"
            size="mini"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          >
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span="3" class="grid" :gutter="1">
        <el-button
          size="mini"
          type="primary"
          icon="el-icon-search"
          @click="userSearch()"
          >搜索</el-button
        >
      </el-col>
      <el-col :span="2" class="grid" :gutter="5">
        <el-button size="mini" type="primary" @click="addMembers()"
          >新增</el-button
        >
      </el-col>
      <el-col :span="3" class="grid" :gutter="5">
        <el-upload
          class="upload-demo"
          action="string"
          :on-change="handleChange"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :multiple="false"
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
          accept="csv, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        >
          <el-button size="mini" type="primary">批量导入</el-button>
        </el-upload>
      </el-col>
      <el-col :span="2" class="grid" :gutter="5">
        <el-button size="mini" type="primary" @click="deleteSelectItem(sels)"
          >批量删除</el-button
        >
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      :stripe="true"
      :header-cell-style="{ textAlign: 'center' }"
      @selection-change="handleSelectionChange"
      :border="true"
      ref="multipleTable"
      size="mini"
      width="100%"
    >
      <el-table-column prop="id" label="ID" v-if="false"> </el-table-column>
      <el-table-column type="selection" width="40"> </el-table-column>
      <el-table-column
        label="学号/工号/身份证号"
        prop="card"
        align="center"
        width="145"
      ></el-table-column>
      <el-table-column
        label="人员姓名"
        prop="name"
        align="center"
        width="90"
      ></el-table-column>
      <el-table-column
        label="人员类型"
        prop="typeName"
        align="center"
        width="90"
      ></el-table-column>
      <el-table-column
        label="联系电话"
        prop="phone"
        align="center"
        width="100"
      ></el-table-column>
      <el-table-column
        label="入校时间"
        prop="createTime"
        align="center"
        width="100"
      ></el-table-column>
      <el-table-column
        label="管理有效期"
        prop="expiry"
        align="center"
        width="95"
      ></el-table-column>
      <el-table-column
        label="身份状态"
        prop="state"
        :formatter="stateFormat"
        align="center"
        width="80"
      ></el-table-column>
      <el-table-column label="备注" prop="info" width="150"></el-table-column>
      <el-table-column label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="modifyData(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button size="mini" type="danger" @click="deleteData(scope.row)"
            >移除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="centerDialogVisible">
      <el-form size="mini" :model="editForm">
        <el-form-item label="人员类型">
          <!-- 下拉框 -->
          <el-select v-model="editForm.typeName">
            <el-option
              v-for="item in optionsb"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="当前状态">
          <!-- 下拉框 -->
          <el-select v-model="editForm.state">
            <el-option
              v-for="item in optionsc"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入校时间" :picker-options="pickerOptions">
          <el-date-picker
            v-model="editForm.createTime"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="工号/学号/身份证号">
          <el-input v-model="editForm.card"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="管理有效期">
          <el-input v-model="editForm.expiry"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="editForm.info"></el-input>
        </el-form-item>
      </el-form>
      <div>
        <el-button size="mini" @click="closeDialog()">取消</el-button>
        <el-button size="mini" type="primary" @click="sumbitEditRow()"
          >确定</el-button
        >
      </div>
    </el-dialog>
    <el-dialog :visible.sync="isAddMembers">
      <el-form size="mini" :model="addForm" :rules="rules" ref="roleForm">
        <el-form-item label="人员类型" prop="pertype">
          <!-- 下拉框 -->
          <el-select v-model="addForm.pertype">
            <el-option
              v-for="item in optionsb"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="当前状态" prop="idstatus">
          <!-- 下拉框 -->
          <el-select v-model="addForm.idstatus">
            <el-option
              v-for="item in optionsc"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="入校时间"
          :picker-options="pickerOptions"
          prop="date1"
        >
          <el-date-picker
            v-model="addForm.date1"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="姓名" prop="pername">
          <el-input v-model="addForm.pername"></el-input>
        </el-form-item>
        <el-form-item label="工号/学号/身份证号" prop="perid">
          <el-input v-model="addForm.perid"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="pernum">
          <el-input v-model="addForm.pernum"></el-input>
        </el-form-item>
        <el-form-item label="管理有效期" prop="effec">
          <el-input v-model="addForm.effec"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="addForm.remarks"></el-input>
        </el-form-item>
      </el-form>
      <div>
        <el-button size="mini" @click="closeDialog()">取消</el-button>
        <el-button size="mini" type="primary" @click="sumbitAddRow('roleForm')"
          >确定</el-button
        >
      </div>
    </el-dialog>
    <!--分页 -->
    <pagination
      v-bind:total="pagecount"
      :page="page"
      @handleSizeChange="handleSizeChange"
      @handleCurrentChange="handleCurrentChange"
    ></pagination>
  </div>
</template>
<script>
import pagination from "@/views/pagination/index.vue";
import { deleteUser, getFindByPage, deleteUserIds } from "@/api/Management";
import * as userInfo1 from "@/api/user";
var _index;
export default {
  name: "HelloWorld",
  components: {
    pagination: pagination,
  },
  data() {
    return {
      msg: "Welcome to Your Vue.js App",
      centerDialogVisible: false,
      multipleSelection: [],
      isAddMembers: false,
      addForm: [],
      editForm: [],
      searchData: "",
      input: "",
      inputs: "",
      id: "",
      sels: [],
      ids: [],
      page: [],
      value: "",
      valuetime: 0,
      value1: [],
      value2: [],
      rules: {
        pertype: [
          { required: true, message: "请选择人员类型", trigger: "change" },
        ],
        idstatus: [
          { required: true, message: "请选择人员当前状态", trigger: "change" },
        ],
        date1: [
          {
            required: true,
            message: "请选择日期",
            trigger: "change",
          },
        ],
        pername: [{ required: true, message: "请填写姓名", trigger: "blur" }],
        perid: [
          {
            required: true,
            message: "请填写工号/学号/身份证号",
            trigger: "blur",
          },
        ],
        pernum: [
          { required: true, message: "请填写联系电话", trigger: "blur" },
        ],
        effec: [
          { required: true, message: "请填写管理有效期", trigger: "blur" },
        ],
      },
      optionsa: [{ label: "陕西职业技术学院", value: "1" }],
      //
      optionsb: [
        { label: "教职工", value: "1" },
        { label: "学生", value: "2" },
        { label: "访客", value: "3" },
      ],
      optionsc: [
        { label: "离校", value: "0" },
        { label: "在校", value: "1" },
      ],
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
      },
      tableData: [],
      file: "",
      fileTemp: "",
      fileList: [],
      pagecount: 0,
      params: {
        name: this.input,
        card: this.inputs,
        typeId: this.value1,
        state: this.value2,
        startTime: this.valuetime,
        pageCurrent: 1,
        pageSize: 10,
      },
    };
  },
  created() {
    //查询人员信息
    let params1 = {
      pageCurrent: 1,
      pageSize: 10,
    };
    this.getUserList(params1);
  },
  methods: {
    //判断校友状态
    stateFormat(row, column) {
      if (row.state == 1) {
        return "在校";
      } else {
        return "离校";
      }
    },
    // 切换每页
    handleSizeChange(size) {
      this.params.pageCurrent = size;
      this.getUserList(this.params);
      console.log(" this.limit:", this.limit);
    },
    // 切换页码
    handleCurrentChange(index) {
      this.params.pageSize = index;
      // console.log(this.params);
      this.getUserList(this.params);
      // console.log(" this.page:", this.page);
    },
    // 请求用户列表的数据
    getUserList(params1) {
      // console.log(params1);
      let params = params1;
      getFindByPage(params).then((data) => {
        this.pagecount = data.data.total;
        this.tableData = data.data.records;
      });
    },
    //搜索
    userSearch() {
      this.params.pageCurrent = 1;
      this.params.name = this.input;
      this.params.card = this.inputs;
      this.params.typeId = this.value1;
      this.params.state = this.value2;
      if (this.valuetime != null) {
        this.params.startTime = this.valuetime[0];
      } else {
        this.$delete(this.params, "startTime");
      }
      // console.log(this.inputs);
      userInfo1.getfindByPage(this.params).then((data) => {
        // console.log(data.data);
        this.pagecount = data.data.total;
        this.tableData = data.data.records;
        // console.log(this.tableData);
      });
    },
    // upload文件上传
    handleChange(file, fileList) {},
    // 文件列表移除文件时的钩子
    handleRemove(file, fileList) {
      this.fileTemp = null;
      console.log(file, fileList);
    },
    // 点击文件列表中已上传的文件时的钩子
    handlePreview(file) {
      console.log(file);
    },
    // 文件超出个数限制时的钩子
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件,本次选择了 ${files.length} 个文件,共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    // 删除文件之前的钩子
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}?`);
    },
    //获取多选的数据
    // 利用多选改变函数提取数组中的“id”，组成字符串
    // handleSelectionChange(val) {
    //   this.multipleSelection = val;
    //   this.idStr = val.map((item) => item.id).toString();
    //   console.log(this.idStr);
    // },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      let ids = [];
      this.multipleSelection.map((item) => {
        ids.push(item.id);
      });
      this.selectedIDs = ids;
      console.log("多选", this.selectedIDs);
    },
    // 批量删除
    deleteSelectItem() {
      this.$confirm("此操作将永久删除该角色, 是否继续?", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        center: true,
      })
        .then(() => {
          this.idStr = this.idStr.split(",");
          deleteUserIds(this.idStr)
            .then(() => {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
            })
            .catch(() => {
              this.$message.error(res.message || "删除失败");
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 删除操作
    deleteData(row) {
      this.$confirm("确定删除吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteUser({ id: row.id })
          .then((response) => {
            this.$notify({
              title: "删除成功",
              message: "成功提示!",
              type: "success",
            });
            if (response.status == 200) {
              //查询人员信息
              let params1 = {
                pageCurrent: 1,
                pageSize: 10,
              };
              this.getUserList(params1);
              this.centerDialogVisible = false;
            } else {
              console.log("删除失败的原因:" + response.data.message);
            }
          })
          .catch((error) => {
            console.log("删除未成功的原因:", error);
          });
      });
    },
    // 修改对象数据
    modifyData(index, row) {
      this.centerDialogVisible = true;
      if (row.state == 0) {
        row.state = "离校";
      } else {
        row.state = "在校";
      }
      this.editForm = Object.assign({}, row); //重置对象
      console.log(this.editForm);
      _index = index;
    },
    // 通过scope.$index锁定修改行的索引，保证所选行的数据可以被成功修改
    // 提交编辑
    sumbitEditRow() {
      var editData = _index;
      console.log("editData的值" + this.editForm);
      if (this.editForm.pername == "") {
      } else if (this.editForm.perid == "") {
      } else if (this.editForm.pernum == "") {
      } else if (this.editForm.effec == "") {
      } else {
        if (this.editForm.typeName == "老师") {
          this.editForm.typeName = 1;
        } else if (this.editForm.typeName == "学生") {
          this.editForm.typeName = 2;
        } else {
          this.editForm.typeName = 3;
        }
        if (this.editForm.state == "离校") {
          this.editForm.state = 0;
        } else {
          this.editForm.state = 1;
        }
        var editMembers1 = {
          id: this.editForm.id,
          name: this.editForm.name,
          type: this.editForm.typeName,
          state: this.editForm.state,
          card: this.editForm.card,
          phone: this.editForm.phone,
          expiry: this.editForm.expiry,
          remark: this.editForm.remark,
          createTime: this.editForm.createTime,
        };
        userInfo1
          .getEdit(editMembers1)
          // 人员信息编辑接口
          .then((data) => {
            console.log("修改结果:", data.status);
            if (data.status == 200) {
              //查询人员信息
              let params1 = {
                pageCurrent: 1,
                pageSize: 10,
              };
              this.getUserList(params1);
              this.centerDialogVisible = false;
            } else {
            }
          })
          .catch((error) => {
            console.log("失败的原因:" + error);
          });
        this.centerDialogVisible = true;
      }
    },
    closeDialog() {
      this.centerDialogVisible = false;
      this.isAddMembers = false;
      console.log("editform", this.editForm);
      console.log("addform", this.addForm);
    },
    // 增加操作
    addMembers() {
      this.isAddMembers = true;
      this.addForm = {
        pertype: "",
        pername: "",
        perid: "",
        date: "",
        idstatus: "",
        pernum: "",
        effec: "",
        remarks: "",
        createTime: "",
      };
    },
    // 保存增加的数据到tableData对象数组
    sumbitAddRow() {
      this.tableData = this.tableData || [];
      if (this.addForm.pername == "") {
      } else if (this.addForm.perid == "") {
      } else if (this.addForm.pernum == "") {
      } else if (this.addForm.effec == "") {
      } else {
        var addMembers1 = {
          name: this.addForm.pername,
          type: this.addForm.pertype,
          state: this.addForm.idstatus,
          card: this.addForm.perid,
          phone: this.addForm.pernum,
          expiry: this.addForm.effec,
          remark: this.addForm.remarks,
          createTime: this.addForm.date1,
        };
        userInfo1
          .getAdd(addMembers1)
          .then((data) => {
            this.pagecount = data.data.total;
            this.tableData = data.data.records;
            if (data.status == 200) {
              this.$notify({
                title: "添加成功",
                message: "成功提示!",
                type: "success",
              });
              //查询人员信息
              let params1 = {
                pageCurrent: 1,
                pageSize: 10,
              };
              this.getUserList(params1);
              this.isAddMembers = false;
            } else {
              console.log(data.msg);
            }
          })
          .catch(function (error) {
            console.log(error);
          });
        this.isAddMembers = false;
      }
    },
  },
  computed: {},
};
</script>
<style scoped>
.demonstration {
  font-size: 12px;
}
.el-row {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  background-color: #f9fafc;
}
.grid-content {
  padding: 10px 10px 0 10px;
}
.table-grid-content {
  border-radius: 4px;
  height: 50px;
  padding: 10px;
}
.el-table {
  margin: 10px 10px 0 10px;
}
</style>