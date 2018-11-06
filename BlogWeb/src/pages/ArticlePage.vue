<template>
  <div>
    <md-editor></md-editor>
  </div>

</template>

<script>
  import SideNavBar from "../components/SideNavBar";
  import BlogShow from "../components/BlogShow";
  import Bottom from "../components/Bottom";
  import MdEditor from "../components/MdEditor";

  export default {
    components: {
      MdEditor,
      Bottom,
      BlogShow,
      SideNavBar},
      name: "article-page"
    }
</script>

<style scoped>

</style>
