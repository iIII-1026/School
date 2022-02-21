<template>
  <div class="EchartPractice">
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="grid-content bg-purple">
          <div class="num">
            <span>{{ formData.totalUser }}</span>
          </div>
          <span>累计管理校内总人数</span>
        </div></el-col
      >
      <el-col :span="6"
        ><div class="grid-content bg-purple">
          <div class="num">
            <span>{{ formData.totalServiceUser }}</span>
          </div>
          <span>累计服务出入人次</span>
        </div></el-col
      >
      <el-col :span="6"
        ><div class="grid-content bg-purple">
          <div class="num">
            <span>{{ formData.totalFaculty }}</span>
          </div>
          <span>当前管理教职工人数</span>
        </div></el-col
      >
      <el-col :span="6"
        ><div class="grid-content bg-purple">
          <div class="num">
            <span>{{ formData.totalStudent }}</span>
          </div>
          <span>当前管理学生人数</span>
        </div></el-col
      >
    </el-row>
    <el-row>
      <el-col :span="12"
        ><div class="grid-contents bg-purple">
          <firstline></firstline>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-contents bg-purple-light">
          <secondline></secondline>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-contents bg-purple-dark">
          <thirdline></thirdline>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { finData } from "@/api/statistics";
import firstline from "./components/firstline.vue";
import secondline from "./components/secondline.vue";
import thirdline from "./components/thirdline.vue";

export default {
  name: "EchartPractice",
  components: {
    firstline,
    secondline,
    thirdline,
  },
  data() {
    return {
      formData: {},
    };
  },
  mounted() {},
  created() {
    this.getfinDataList();
  },
  methods: {
    // 获取数据列表
    getfinDataList() {
      finData().then((res) => {
        this.formData = res.data;
        // console.log(res, "+++");
      });
    },
  },
  mounted() {},
  compunted: {},
};
</script>

<style scoped>
span {
  width: 200px;
  height: 100px;
}
.grid-content {
  border-style: groove;
  text-align: center;
  border-radius: 4px;
  min-height: 36px;
  margin: 10px 1px 10px 10px;
  padding: 10px;
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.num {
  font-size: 30px;
}
</style>
