<template>
  <div>
    <div class="textthird">
      <span>各出入口人次年度统计</span>
      <el-select v-model="Typevalue" placeholder="请选择" style="width: 20%">
        <el-option
          size="small"
          v-for="item in selectData"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        >
        </el-option>
      </el-select>
    </div>
    <div id="thmain" style="width: 100%; height: 400%"></div>
  </div>
</template>
<script>
import { findMonths } from "@/api/statistics";
export default {
  name: "",
  components: {},

  data() {
    return {
      Typevalue: "",
      // labYearlist: "",
      selectData: [
        { name: "入口1", id: 4 },
        { name: "出口1", id: 3 },
        { name: "入口2", id: 2 },
        { name: "出口2", id: 1 },
      ],
    };
  },
  mounted() {
    this.drawChart();
  },
  created() {},

  methods: {
    drawChart() {
      // findMonths({ qrId: this.Typevalue }).then((res) => {
      //   this.arrList = res.data;
      //   console.log(res, "数据");
      // });
      let myEchart = this.$echarts.init(document.getElementById("thmain"));
      let option = {
        legend: {},
        tooltip: {},
        dataset: {
          dimensions: ["peoplenum", "总人数", "教职工", "学生", "访客"],
          source: [
            {
              peoplenum: "一月",
              总人数: "",
              教职工: "",
              学生: "",
              访客: "",
            },
            {
              peoplenum: "二月",
              总人数: "",
              教职工: "",
              学生: "",
              访客: "",
            },
            {
              peoplenum: "三月",
              总人数: "",
              教职工: "",
              学生: "",
              访客: "",
            },
            {
              peoplenum: "四月",
              总人数: "",
              教职工: "",
              学生: "",
              访客: "",
            },
            {
              peoplenum: "五月",
              总人数: "",
              教职工: "",
              学生: "",
              访客: "",
            },
            {
              peoplenum: "六月",
              总人数: "",
              教职工: "",
              学生: "",
              访客: "",
            },
            {
              peoplenum: "七月",
              总人数: "",
              教职工: "",
              学生: "",
              访客: "",
            },
            {
              peoplenum: "八月",
              总人数: "",
              教职工: "",
              学生: "",
              访客: "",
            },
            {
              peoplenum: "九月",
              总人数: "",
              教职工: "",
              学生: "",
            },
            {
              peoplenum: "十月",
              总人数: "",
              教职工: "",
              学生: "",
            },
            {
              peoplenum: "十一月",
              总人数: "",
              教职工: "",
              学生: "",
            },
            {
              peoplenum: "十二月",
              总人数: "",
              教职工: "",
              学生: "",
            },
          ],
        },
        xAxis: { type: "category" },
        yAxis: {},
        series: [
          { type: "bar" },
          { type: "bar" },
          { type: "bar" },
          { type: "bar" },
        ],
      };

      option && myEchart.setOption(option);
    },
  },
  watch: {},
  computed: {},
};
</script>

<style lang='scss' scoped>
.textthird {
  align-items: center;
  justify-content: space-around;
  display: flex;
  width: 100%;
  span {
    width: 70%;
  }
}
</style>
