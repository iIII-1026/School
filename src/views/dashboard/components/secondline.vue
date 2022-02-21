<template>
  <div>
    <div class="textsecond">
      <span>各出入口不同角色人次对比</span>
      <el-date-picker
        v-model="Typevalue"
        type="month"
        size="small"
        placeholder="选择月"
      >
      </el-date-picker>
    </div>
    <div id="semain" style="width: 100%; height: 400%"></div>
  </div>
</template>
<script>
import { findRole } from "@/api/statistics";
export default {
  name: "EchartPractice",
  data() {
    return {
      Typevalue: "",
      arrList: [],
    };
  },
  mounted() {
    this.drawChart();
  },
  methods: {
    drawChart() {
      findRole({ date: this.Typevalue }).then((res) => {
        this.arrList = res.data;
        var totallist = [];
        var total = [];
        let yData = [];
        let rukou1 = [],
          rukou2 = [],
          chukou1 = [],
          chukou2 = [];
        for (var i = 0; i < this.arrList.length; i++) {
          this.arrList[i].difRoleMonthVOS.forEach((e) => {
            if (e.pip == "陕职-入口1") rukou1.push(e.userTotal);
            if (e.pip == "陕职-入口2") rukou2.push(e.userTotal);
            if (e.pip == "陕职-出口1") chukou1.push(e.userTotal);
            if (e.pip == "陕职-出口2") chukou2.push(e.userTotal);
          });
          totallist.push(total);
          total = [];
          yData.push(this.arrList[i].time);
        }
        var pip = [];
        var serieslist = [];
        for (var j = 0; j < this.arrList.length; j++) {
          var series = {
            name: "",
            type: "bar",
            stack: "Total",
            data: [],
          };
          pip.push(this.arrList[j].month);
          series.name = this.arrList[j].month;
          series.type = "bar";
          series.stack = "Total";
          series.data = totallist[j];
          serieslist.push(series);
        }
        let myEchart = this.$echarts.init(document.getElementById("semain"));
        let option = {
          title: {
            show: false,
          },
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow",
            },
          },
          legend: {
            data: this.pip,
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: {
            type: "value",
            min: 0,
            boundaryGap: [0, 0.01],
          },
          yAxis: {
            type: "category",
            data: ["学生", "访客", "教职工"],
          },
          series: serieslist,
          series: [
            {
              name: "陕职-入口1",
              type: "bar",
              stack: "Total",
              data: rukou1,
            },
            {
              name: "陕职-出口1",
              type: "bar",
              data: chukou1,
            },
            {
              name: "陕职-入口2",
              type: "bar",
              data: rukou2,
            },
            {
              name: "陕职-出口2",
              type: "bar",
              data: chukou2,
            },
          ],
        };
        // console.log(option.series);
        option && myEchart.setOption(option);
      });
    },
  },
};
</script>

<style lang='scss' scoped>
.textsecond {
  text-align: center;
  align-items: center;
  display: flex;
  width: 100%;
  span {
    width: 70%;
  }
}
</style>
