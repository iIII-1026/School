<template>
  <div>
    <div class="textsecond">
      <span>各出入口不同角色人次对比 </span>
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
      labPeolist: "",
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
        console.log(this.arrList[0].pip);
        var rukou1 = [];
        var rukou2 = [];
        var chukou1 = [];
        var chukou2 = [];
        for (var i = 0; i < this.arrList.length; i++) {
          if (this.arrList[i].pip === "陕职-入口1") {
            rukou1.push(this.arrList[i].userTotal);
          } else if (this.arrList[i].pip === "陕职-入口2") {
            rukou2.push(this.arrList[i].userTotal);
          } else if (this.arrList[i].pip === "陕职-出口1") {
            chukou1.push(this.arrList[i].userTotal);
          } else if (this.arrList[i].pip === "陕职-出口2") {
            chukou2.push(this.arrList[i].userTotal);
          }
        }
        console.log(rukou1);
        console.log(rukou2);
        console.log(chukou1);
        console.log(chukou2);
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
            data: ["陕职-入口1", "陕职-出口1", "陕职-入口2", "陕职-出口2"],
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
            //   max: 1000,
            boundaryGap: [0, 0.01],
          },
          yAxis: {
            type: "category",
            data: ["教职工", "学生", "访客"],
          },
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
              data: rukou2,
            },
          ],
        };
        // axios.get("/").then((res) => {
        //   console.log("secondline访问后台");
        //   // console.log(res.data);
        //   this.labPeolist = res.data.data.labPeolist;
        //   console.log(this.labPeolist);
        //   // this.labPeolist = eval('('+data+')');
        //   this.option({
        //     series: [
        //       {
        //         name: "入口1",
        //         type: "bar",
        //         stack: "Total",
        //         data: this.labPeolist,
        //       },
        //       {
        //         name: "出口1",
        //         type: "bar",
        //         stack: "Total",
        //         data: this.labPeolist,
        //       },
        //       {
        //         name: "入口2",
        //         type: "bar",
        //         stack: "Total",
        //         data: this.labPeolist,
        //       },
        //       {
        //         name: "出口2",
        //         type: "bar",
        //         stack: "Total",
        //         data: this.labPeolist,
        //       },
        //     ],
        //   });
        // });
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
