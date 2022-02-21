<template>
  <div>
    <div class="textfirst">
      <span>各出入口不同时段人数统计 </span>
      <el-time-select
        v-model="Typevalue"
        type="hour"
        size="small"
        :picker-options="{
          start: '08:30',
          step: '02:00',
          end: '18:30',
        }"
        placeholder="选择时间"
      >
      </el-time-select>
    </div>
    <div id="main" style="width: 100%; height: 400%"></div>
  </div>
</template>

<script>
import { findHours } from "@/api/statistics";

export default {
  name: "",
  components: {},

  data() {
    return {
      Typevalue: "",
      arrList: [],
    };
  },
  created() {},
  mounted() {
    this.drawChart();
  },
  methods: {
    drawChart() {
      findHours({ date: this.Typevalue }).then((res) => {
        this.arrList = res.data;
        var totallist = [];
        var total = [];
        let xData = [];
        let yData1 = [],
          yData2 = [],
          yData3 = [],
          yData4 = [];
        for (var j = 0; j < this.arrList.length; j++) {
          this.arrList[j].difDateTimeVOs.forEach((e) => {
            if (e.pip == "陕职-出口1") yData1.push(e.userTotal);
            if (e.pip == "陕职-出口2") yData2.push(e.userTotal);
            if (e.pip == "陕职-入口1") yData3.push(e.userTotal);
            if (e.pip == "陕职-入口2") yData4.push(e.userTotal);
          });
          totallist.push(total);
          total = [];
          xData.push(this.arrList[j].time);
        }
        var pip = [];
        var serieslist = [];
        for (var i = 0; i < this.arrList.length; i++) {
          var series = {
            name: "",
            type: "line",
            stack: "Total",
            data: [],
          };
          pip.push(this.arrList[i].hour);
          series.name = this.arrList[i].hour;
          series.type = "line";
          series.stack = "Total";
          series.data = totallist[i];
          serieslist.push(series);
        }
        let myEchart = this.$echarts.init(document.getElementById("main"));
        let option = {
          title: {
            show: false,
          },
          tooltip: {
            trigger: "axis",
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
          toolbox: {
            show: false,
          },
          xAxis: {
            type: "category",
            boundaryGap: false,
            data: xData,
          },
          yAxis: {
            type: "value",
          },
          series: serieslist,
          series: [
            {
              name: "陕职 - 入口1",
              type: "line",
              stack: "Total",
              data: yData3,
            },
            {
              name: "陕职 - 出口1",
              type: "line",
              stack: "Total",
              data: yData1,
            },
            {
              name: "陕职 - 入口2",
              type: "line",
              stack: "Total",
              data: yData4,
            },
            {
              name: "陕职 - 出口2",
              type: "line",
              stack: "Total",
              data: yData2,
            },
          ],
        };
        // console.log(option.series);
        option && myEchart.setOption(option);
      });
    },
  },
  watch: {},
  computed: {},
};
</script>

<style lang='scss' scoped>
.textfirst {
  align-items: center;
  display: flex;
  width: 100%;
  span {
    width: 70%;
  }
}
</style>
