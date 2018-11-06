<template>
  <div>
    <div style="position: fixed; left: 0; top: 0; width: 100%; height: 650px; background-color: #4cb4e7"></div>
    <main-nav-bar></main-nav-bar>
    <side-nav-bar></side-nav-bar>
    <blog-show></blog-show>
  </div>

</template>

<script>
    import SideNavBar from "../components/SideNavBar";
    import BlogShow from "../components/BlogShow";
    import Bottom from "../components/Bottom";
    import MdEditor from "../components/MdEditor";
    import MainNavBar from "../components/MainNavBar";

    export default {
      components: {
        MainNavBar,
        MdEditor,
        Bottom,
        BlogShow,
        SideNavBar},
      name: "main-page"
    }
</script>

<style scoped>

</style>
