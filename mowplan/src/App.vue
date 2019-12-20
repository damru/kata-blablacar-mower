<template>
  <div id="app">
    <MowingPlan />
  </div>
</template>

<script>
import MowingPlan from './components/MowingPlan.vue'

export default {
  name: 'app',
  components: {
    MowingPlan
  }
}
</script>

<style>
body {
  margin: 0;
  background-color: #45c945 !important;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>
