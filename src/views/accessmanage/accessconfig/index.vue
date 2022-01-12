<template>
  <div class="hello">
    <div class="header">
     <el-row :gutter="20">
        <el-col :span="5">
          <el-input
            size="small"
            clearable
            placeholder="打卡名称"
            v-model="queryDate.pip">
          </el-input>
      </el-col>
      <!-- <el-col :span="7">
         <el-select v-model="queryDate.name" size="small" placeholder="状态" style="width:100%">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
      </el-col> -->
      <el-col :span="5">
         <el-select clearable v-model="queryDate.ctype" size="small" placeholder="打卡类型" style="width:100%">
            <el-option
              v-for="item in optionsc"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
      </el-col>
       <el-col :span="10">
          <el-date-picker
          clearable
          style="width:100%"
            v-model="valuetime"
            value-format="yyyy-MM-dd-HH:mm:ss"
            size="small"
            type="datetimerange"
            range-separator="至"
            start-placeholder="创建开始日期"
            end-placeholder="创建结束日期">
          </el-date-picker>
      </el-col>
        <el-col :span="4">
       <el-button size="small" type="primary" icon="el-icon-search"@click="userSearch()">搜索</el-button>
        <el-button size="small" type="primary" @click="addMembers">新增</el-button>
      </el-col>
    </el-row>
     <el-row :gutter="20">

       </el-row>
    </div>
    <div class="tableBox">
      <el-table
      :data="FindByList"
      :header-cell-style="{background:'#eef1f6',color:'#606266'}"
      border
      width="100%">
      <el-table-column
        label="打卡地点名称"
        prop="pip"
        align="center"
        width="200">
      </el-table-column>
      <el-table-column
        label="打卡类型"
        prop="ctype"
        align="center"
        width="150">
      </el-table-column>
      <!-- <el-table-column
        prop="state"
        label="打卡二维码"
        width="150"
        align="center">
        <template slot-scope="scope">
            <div class="qrImgbox" @click="qrmgBtn"><img :src="`https://schooldata.aizhixin.com${scope.row.photo}`" alt=""></div>
        </template>
        </el-table-column> -->
      <el-table-column
        label="打卡人次"
        prop="total"
        align="center"
        width="100">
      </el-table-column>
       <el-table-column
        label="创建日期"
        prop="createTime"
        align="center"
        width="120">
      </el-table-column>
       <el-table-column
        label="备注"
        prop="remark"
        align="center"
        width="150">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="modifyData(scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteData(scope.row)">移除</el-button>
             <el-button
            size="mini"
            type="warning"
            @click="qrmgBtn(scope.row)">下载二维码</el-button>
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
    :visible.sync="isAddMembers">
      <el-form :model="addForm" :rules="rules" ref="roleForm" label-width="120px">
        <el-form-item label="地点类型" prop="ctype">
          <!-- 下拉框 -->
          <el-select v-model="addForm.ctype">
            <el-option
              v-for="item in optionsc"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
         <el-form-item label="打卡点名称" prop="pip">
          <el-input v-model="addForm.pip"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" :rows="3" v-model="addForm.remark"></el-input>
        </el-form-item>
      </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button size="mini" @click="addRoleCancel()">取消</el-button>
          <el-button size="mini" type="primary" @click="sumbitAddRow('roleForm')">确定</el-button>
        </span>
    </el-dialog>
  </div>
</template>
<script>
import Pagination from "@/components//Pagination/index.vue";
import { qrFindByPage,addQr,updateQr,qrDeleteById,exportSaveImg } from "@/api/Management";
export default {
  name: "HelloWorld",
  components: {
    Pagination
  },
  data() {
    return {
      valuetime:"",
      total:0, //总条数
      pageQuery:{
         pageSize:10, //当前显示多少数据
         pageCurrent:1,	// 当前页
      },
      queryDate:{
        ctype:"", //打卡类型
        pip:"", // 打卡点名称
        startTime:"", //分页-创建起时间
        endTime:"", // 	分页-创建止时间
      },
      selectType:0, // 0:新增 1:修改 2: 查看
      FindByList:[], //列表数据
      isAddMembers: false,
      titleShow:"新增出入配置", //
      addForm: {
        ctype:"", //打卡类型
        id:"", //主键id
        photo:"", //图片
        id:"", // 主键id
        pip:"", // 打卡地点名称
        remark:"",  //备注
      },
       rules:{
            ctype:[
                {required: true, message: '请选择人地点类型', trigger: 'change' },
            ],
            pip:[
                {required: true, message: '请输入打卡点名称', trigger: 'blur' },
            ],
        },
        optionsc: [
        { label: "入校", value: "入校" },
        { label: "出校", value: "出校" },
      ],
      options: [
        {
          value: "进校",
          label: "进校",
        },
        {
          value: "离校",
          label: "离校",
        },
        {
          value: "校内",
          label: "校内",
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
    this.getFindByList()
  },
  methods: {
     // 获取数据列表
          getFindByList(){
            this.queryDate.startTime = this.valuetime[0] || ""
            this.queryDate.endTime= this.valuetime[1] || ""
            let parems = Object.assign(this.queryDate,this.pageQuery)
            qrFindByPage(parems).then(res=>{
              this.FindByList = res.data.records  //列表数据
              this.total = res.data.total  //总条数
              this.pageQuery.pageCurrent = res.data.current   //当前页
              this.pageQuery.pageSize = res.data.size  //每页显示条数
            })
          },
    //搜索
    userSearch() {
      this.getFindByList()
    },
    //点击下载二维码
    qrmgBtn(row){
        exportSaveImg({id:row.id});
    },
    //新增按钮
    addMembers(){
      this.selectType = 0
      this.titleShow="新增出入配置"
      this.addForm = {}
      this.isAddMembers = true;
    },
    //编辑按钮
    modifyData(row) {
      this.titleShow="修改出入配置"
      this.selectType = 1
      this.isAddMembers = true;
      this.addForm = {
        ctype:row.ctype, //打卡类型
        id:row.id, //主键id
        photo:row.photo, //图片
        id:row.id, // 主键id
        pip:row.pip, // 打卡地点名称
        remark:row.remark,  //备注
      }
      },
    //关闭弹窗
    addRoleCancel(){
      this.isAddMembers = false;
    },
  //确定新增按钮
    sumbitAddRow(formName) {
        if(this.selectType == 0){  //新增操作
          this.$refs[formName].validate((valid) => {
            if (valid) {
              addQr(this.addForm).then(res=>{
                this.$notify({
                title: '添加出入配置成功',
                message: '成功提示!',
                type: 'success'
              });
               this.getFindByList()
               this.isAddMembers= false
            })
            } else {
              console.log('error submit!!');
              return false;
            }
      });
        }else if(this.selectType == 1){ //修改操作
          this.$refs[formName].validate((valid) => {
          if (valid) {
            updateQr(this.addForm).then(res=>{
              this.$notify({
              title: '修改出入配置成功',
              message: '成功提示!',
              type: 'success'
            });
               this.getFindByList()
               this.isAddMembers= false
          })
          } else {
            console.log('error submit!!');
            return false;
          }
      });
        }
    },
    // 删除操作
    deleteData(row) {
       this.$confirm('确定要删除该出入配置吗？, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                    }).then(() => {
                       qrDeleteById({id:row.id}).then(res=>{
                         this.getFindByList()
                          this.$notify({
                          title: '删除成功',
                          message: '成功提示!',
                          type: 'success'
                        });
                      })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                });
    },
    },
  computed: {

  },
};
</script>
<style lang="scss" scoped>
.tableBox{
  margin-top: 40px;
}
.qrImgbox{
  width: 50px;
  height: 50px;
  cursor: pointer;
  img{
    width: 100%;
    height: 100%;
  }
}
</style>
