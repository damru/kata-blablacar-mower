const BASE_URL = process.env.VUE_APP_BASE_URL;
const HOST = window.location.host;

const MOWER_API_URL = process.env.VUE_APP_MOWER_API_URL;

export default {
  resourceServer: {
    app: {
      host: `${HOST}`,
      baseUrl: `${BASE_URL}`
    },
    mowerApi: {
        url: `${MOWER_API_URL}`
    }
  }
}