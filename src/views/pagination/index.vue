<template>
  <div>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      class="footerpage"
      background
      layout="sizes,prev, pager, next,total"
      :page-sizes="[10, 20, 50, 100, 500, 1000]"
      :page-size="10"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "",
  components: {},
  props: ["total"],
  data() {
    return {};
  },
  created() {},
  mounted() {},
  methods: {
    // 切换页码
    handleCurrentChange(size) {
      this.$emit("handleSizeChange", size);
      console.log(`当前页: ${size}`);
    },
    // 切换每页显示的数量
    handleSizeChange(index) {
      this.$emit("handleCurrentChange", index);
      console.log(`每页 ${index} 条`);
    },
  },
  watch: {},
  computed: {},
};
</script>

<style lang='scss' scoped>
.footerpage {
  padding-top: 3%;
  text-align: center;
}
</style>
