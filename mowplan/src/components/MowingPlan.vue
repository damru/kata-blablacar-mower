<template>
  <section class="container">
    <div class="form-part" ref="lawn-config">
      <div class="hero">
        <h1>Get ready for a nice clean mowing of your lawn</h1>
        <img src="https://i.giphy.com/media/c3TWQwnnWtLnq/source.gif" />
      </div>
      <form>
        <div class="custom-file">
          <input type="file" @change="setLawnConfigFile()" ref="lawnConfig__form__file" class="custom-file-input" id="lawnConfig__form__file" />
          <label class="custom-file-label" for="lawnConfig__form__file">Choose file</label>
        </div>
        <input type="button" :disabled="lawnConfigFile === null" class="btn btn-dark" @click="sendLawnConfig(this); scrollTo('lawn-result')" value="Let's mow"/>
      </form>
    </div>
    <div class="form-part" ref="lawn-result">
      <div class="hero">
        <h1>Where are all the mowers now?</h1>
        <img style="border: 3px solid black;" src="https://image.freepik.com/free-photo/green-grass-field-background-soccer-football-sports-green-lawn-texture-background_64749-2455.jpg" />
      </div>
      <template v-if="mowers">
        <div v-for="mower in mowers" v-bind:key="mower">
          <span>{{ mower.x }} {{ mower.y }} {{ mower.orientation.charAt(0) }}</span>
        </div>
        <input type="button" class="btn btn-dark" @click="scrollTo('lawn-config')" value="Need another cut? Mow again." />
      </template>
    </div>
  </section>
</template>

<script>
import config from '@/environment'

export default {
  name: 'mowing-plan',
  props: {
  },
  data: function() {
    return {
      mowers: {},
      lawnConfigFile: null
    }
  },
  methods: {
    scrollTo: function(anchor) {
      var element = this.$refs[anchor];
      element.scrollIntoView({behavior: 'smooth'});
    },
    setLawnConfigFile() {
      this.lawnConfigFile = this.$refs.lawnConfig__form__file.files[0]
    },
    sendLawnConfig: async function() {
      let formData = new FormData()
      formData.append('file', this.lawnConfigFile)
      let options = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      let result = await this.$http.post(config.resourceServer.mowerApi.url, formData, options)
      if (result.status === 200) {
        this.mowers = result.data
      }
    }
  }
}
</script>

<style scoped lang="css">
h1 {
  margin: 0;
}

.btn {
  margin: 10px 0;
}

.form-part {
  display: block;
  margin: auto;
  height: 100vh;
  max-width: 900px;
  position: relative;
}
.form-part form {
  max-width: 300px;
  margin: auto;
}

.hero {
  padding-top: 20px;
  height: 50%;
}
.hero h1{
  font-size: 2em;
  padding: 10px 0;
}
.hero img {
  margin: auto;
  height: 100%;
  max-width: 85%;
  max-height: calc(85% - 2em - 20px);
}

</style>
