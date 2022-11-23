const {createProxyMiddleware} = require("http-proxy-middleware");

module.exports = function (app){
    app.use(
        "/eple/v1/mystudent/lecture",
        createProxyMiddleware({
            target: "http://localhost:8080",
            changeOrigin: true,
        })
    );
};





