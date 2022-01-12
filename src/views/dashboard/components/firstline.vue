<template>
  <div>
    <div class="textfirst">
      <span>各出入口不同时段人数统计 </span>
      <el-date-picker
        v-model="Typevalue"
        type="month"
        size="small"
        placeholder="选择月"
        @blur =
      >
      </el-date-picker>
    </div>
    <div id="main" style="width: 105%; height: 400%"></div>
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
      labList: "",
      selectValue: "",
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
        for (var j = 0; j < this.arrList.length; j++) {
          for (var i = 0; i < this.arrList[j].difQrMonthVOS.length; i++) {
            total.push(this.arrList[j].difQrMonthVOS[i].userTotal);
          }

          totallist.push(total);
          total = [];
        }
        console.log(totallist);
        var pip = [];
        var serieslist = [];

        for (var i = 0; i < this.arrList.length; i++) {
          var series = {
            name: "",
            type: "line",
            stack: "Total",
            data: [],
          };
          pip.push(this.arrList[i].month);
          series.name = this.arrList[i].month;
          series.type = "line";
          series.stack = "Total";
          series.data = totallist[i];
          serieslist.push(series);
        }
    //    console.log(serieslist);
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
            data: [
              this.arrList[0].difQrMonthVOS[0].typeName,
              this.arrList[0].difQrMonthVOS[1].typeName,
              this.arrList[0].difQrMonthVOS[2].typeName,
              this.arrList[0].difQrMonthVOS[3].typeName,
              this.arrList[0].difQrMonthVOS[4].typeName,
              this.arrList[0].difQrMonthVOS[5].typeName,
            ],
          },
          yAxis: {
            type: "value",
          },
          series: serieslist,
          // series: [
          //   {
          //     name: "陕职 - 入口1",
          //     type: "line",
          //     stack: "Total",
          //     data: totallist[0],
          //   },
          //   {
          //     name: "陕职 - 入口1",
          //     type: "line",
          //     stack: "Total",
          //     data: totallist[1],
          //   },
          //   //   {
          //   //     name: 陕职 - 入口2,
          //   //     type: "line",
          //   //     stack: "Total",
          //   //     data: totallist[2],
          //   //   },
          //   //   {
          //   //     name: 陕职 - 出口2,
          //   //     type: "line",
          //   //     stack: "Total",
          //   //     data: totallist[3],
          //   //   },
          // ],
        };
        // axios.get("/").then((res) => {
        //   console.log("firstline访问后台");
        //   // console.log(res.data);
        //   this.labList = res.data.data.labList;
        //   console.log(this.labList);
        // this.option({
        //   series: [
        //     {
        //       name: "出口1",
        //       type: "line",
        //       stack: "Total",
        //       data: this.labList,
        //     },
        //     {
        //       name: "入口1",
        //       type: "line",
        //       stack: "Total",
        //       data: this.labList,
        //     },
        //     {
        //       name: "出口2",
        //       type: "line",
        //       stack: "Total",
        //       data: this.labList,
        //     },
        //     {
        //       name: "入口2",
        //       type: "line",
        //       stack: "Total",
        //       data: this.labList,
        //     },
        //   ],
        // });
        console.log(option.series);
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
