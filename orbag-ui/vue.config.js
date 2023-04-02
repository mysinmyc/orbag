const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 9090, 
    proxy: {
      '^/api': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true,
        auth: 'it_user:orbag'
      }
    }
  }
})
