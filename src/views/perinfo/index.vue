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
          action="http://192.168.40.176:9530"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
        >
          <el-button size="mini" type="primary">批量导入</el-button>
        </el-upload>
      </el-col>
      <el-col :span="2" class="grid" :gutter="5">
        <el-button size="mini" type="primary" @click="batchDelete()"
          >批量删除</el-button
        >
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      :stripe="true"
      :header-cell-style="{ textAlign: 'center' }"
      :border="true"
      size="mini"
      width="100%"
    >
      <el-table-column prop="id" label="ID" v-if="false"> </el-table-column>
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
        width="90"
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
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="modifyData(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="deleteData(scope.$index, tableData)"
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
          <!-- <el-input v-model="editForm.idstatus"></el-input> -->
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
      <el-form size="mini" :model="addForm">
        <el-form-item label="人员类型">
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
        <el-form-item label="当前状态">
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
        <el-form-item label="入校时间" :picker-options="pickerOptions">
          <el-date-picker
            v-model="addForm.date"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="addForm.pername"></el-input>
        </el-form-item>
        <el-form-item label="工号/学号/身份证号">
          <el-input v-model="addForm.perid"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="addForm.pernum"></el-input>
        </el-form-item>
        <el-form-item label="管理有效期">
          <el-input v-model="addForm.effec"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="addForm.remarks"></el-input>
        </el-form-item>
      </el-form>
      <div>
        <el-button size="mini" @click="closeDialog()">取消</el-button>
        <el-button size="mini" type="primary" @click="sumbitAddRow()"
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
// import { musicBroadcastingDetails } from '@/api/index.js'
import pagination from "@/views/pagination/index.vue";
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
      isAddMembers: false,
      editForm: [],
      addForm: [],
      searchData: "",
      input: "",
      inputs: "",
      id: "",
      value: "",
      valuetime: 0,
      value1: [],
      value2: [],
      optionsa: [{ label: "陕西职业技术学院", value: "1" }],
      optionsb: [
        { label: "老师", value: "1" },
        { label: "学生", value: "2" },
        { label: "访客", value: "3" },
      ],
      optionsc: [
        { label: "过期", value: "0" },
        { label: "在校", value: "1" },
      ],
      // options: [
      //   {
      //     value: "学生",
      //     label: "学生",
      //   },
      //   {
      //     value: "教职工",
      //     label: "教职工",
      //   },
      // ],
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
      },
      tableData: [
        {
          perid: "20211202",
          pername: "张三",
          pertype: "学生",
          pernum: "1---2---3",
          date: "2020-05-02",
          effec: "4年",
          idstatus: "在校",
          remarks: "xxx",
        },
        {
          perid: "111",
          pername: "张三",
          pertype: "老师",
          pernum: "1---2---3",
          date: "2020-05-02",
          effec: "2年",
          idstatus: "在校",
          remarks: "xxx",
        },
        {
          perid: "20200202",
          pername: "李四",
          pertype: "教职工",
          pernum: "1---2---3",
          date: "2020-05-02",
          effec: "2年",
          idstatus: "在校",
          remarks: "xxx",
        },
      ],
      fileList: [],
      pagecount: "",
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
      console.log(this.params);
      this.getUserList(this.params);
      console.log(" this.page:", this.page);
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

      console.log(this.inputs);
      // console.log(this.params);
      userInfo1.getfindByPage(this.params).then((data) => {
        console.log(data.data);
        this.pagecount = data.data.total;
        this.tableData = data.data.records;
        console.log(this.tableData);
      });
    },
    // 请求用户列表的数据
    getUserList(params1) {
      console.log(params1);
      let params = params1;
      userInfo1.getfindByPage(params).then((data) => {
        this.pagecount = data.data.total;
        this.tableData = data.data.records;
      });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    // 批量导入
    batchImport() {},
    // 批量删除
    batchDelete() {},
    // 删除操作
    deleteData(index, row) {
      console.log(row[index].id);
      this.$confirm("确定删除该条数据?", "提示", {
        type: "warning",
      }).then(() => {
        //   var params = new URLSearchParams();
        //  params.append("id", row.id);
        var deleteparams = [];
        deleteparams = row[index].id;
        // 人员信息移除接口
        userInfo1
          .getdelete(deleteparams)
          .then((response) => {
            console.log("删除的结果：", response);

            if (response.status == 200) {
              alert(response.data);
              // alert(data.msg);
              //查询人员信息
              let params1 = {
                pageCurrent: 1,
                pageSize: 10,
              };
              this.getUserList(params1);
              this.centerDialogVisible = false;
            } else {
              alert("删除失败的原因：" + response.data.message);
            }
          })
          .catch((error) => {
            console.log("删除失败的原因：", error);
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
    sumbitEditRow() {
      var editData = _index;
      console.log("editData的值" + this.editForm);
      if (this.editForm.pername == "") {
        alert("人员姓名不能为空");
      } else if (this.editForm.perid == "") {
        alert("学号/工号/身份证号不能为空");
      } else if (this.editForm.pernum == "") {
        alert("联系电话不能为空");
      } else if (this.editForm.effec == "") {
        alert("有效期不能为空");
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
            console.log("修改结果：", data.status);
            if (data.status == 200) {
              // alert(data.msg);
              alert(data.data);
              //查询人员信息
              let params1 = {
                pageCurrent: 1,
                pageSize: 10,
              };
              this.getUserList(params1);
              this.centerDialogVisible = false;
            } else {
              alert(response.data.message);
            }
          })
          .catch((error) => {
            console.log("失败的原因：" + error);
          });
        this.centerDialogVisible = true;
      }
    },
    closeDialog() {
      this.centerDialogVisible = false;
      this.isAddMembers = false;
      console.log("editfrom", this.editForm);
      console.log("addfrom", this.addForm);
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
      };
    },
    // 保存增加的数据到tableData对向数组,调用数组push()方法
    sumbitAddRow() {
      this.tableData = this.tableData || [];
      if (this.addForm.pername == "") {
        alert("姓名不能为空");
      } else if (this.addForm.perid == "") {
        alert("工号/学号/身份证号不能为空");
      } else if (this.addForm.pernum == "") {
        alert("联系方式不能为空");
      } else if (this.addForm.effec == "") {
        alert("有效期不能为空");
      } else {
        var addMembers1 = {
          name: this.addForm.pername,
          type: this.addForm.pertype,
          state: this.addForm.idstatus,
          card: this.addForm.perid,
          phone: this.addForm.pernum,
          expiry: this.addForm.effec,
          remark: this.addForm.remarks,
          createTime: this.addForm.date,
        };

        userInfo1
          .getAdd(addMembers1)
          .then((data) => {
            this.pagecount = data.data.total;
            this.tableData = data.data.records;
            console.log(this.tableData);
            if (data.status == 200) {
              alert(data.data);
              //查询人员信息
              let params1 = {
                pageCurrent: 1,
                pageSize: 10,
              };
              this.getUserList(params1);
              this.isAddMembers = false;
            } else {
              alert(data.msg);
            }
          })
          .catch(function (error) {
            console.log(error);
          });

        //  this.isAddMembers = false;
      }
    },
  },
  computed: {
    // 查找操作-----查找一个对象数据，在输入框输入内容进行模糊查询
    // tables() {
    //   const input = this.input;
    //   const inputs = this.inputs;
    //   if (input) {
    //     // console.log("input输入的搜索内容：" + this.input);
    //     return this.tableData.filter((data) => {
    //       // console.log("object:" + Object.keys(data));
    //       return Object.keys(data).some((key) => {
    //         return String(data[key]).toLowerCase().indexOf(input) > -1;
    //       });
    //     });
    //   }
    //   if (inputs) {
    //     return this.tableData.filter((data) => {
    //       return Object.keys(data).some((key) => {
    //         return String(data[key]).toLowerCase().indexOf(inputs) > -1;
    //       });
    //     });
    //   }
    //   return this.tableData;
    //  },
  },
};
</script>
<style scoped>
.demonstration {
  font-size: 12px;
}
.el-row {
  margin-bottom: 20px;
  /* &:last-child { */
  margin-bottom: 0;
  /* } */
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  /* padding: 10px 0; */
  background-color: #f9fafc;
}
.grid-content {
  padding: 10px 10px 0 10px;
}
.table-grid-content {
  border-radius: 4px;
  height: 50 px;
  /* background: #ebeef5; */
  padding: 10px;
}
.el-table {
  margin: 10px 10px 0 10px;
}
</style>
