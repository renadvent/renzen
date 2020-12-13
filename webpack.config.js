var path = require("path");
const TerserPlugin = require('terser-webpack-plugin');

module.exports = {
  //entry: './src/main/js/app.js',
  entry: ".\\src\\main\\javascript\\index.js",
  // devtool: "sourcemaps",
  cache: true,
  // mode: "development",
  mode: "production",
  optimization: {
    minimizer: [
      new TerserPlugin({
        /* additional options here */
      }),
    ],
  },
  output: {
    path: __dirname,
    filename: "./src/main/resources/static/built/bundle.js",
  },

  module: {
    rules: [
      {
        test: /\.(jpg|jpeg|png)$/,
        use: {
          loader: "url-loader",
        },
      },
      {
        test: path.join(__dirname, "."),
        exclude: /(node_modules|icons)/,
        use: [
          {
            loader: "babel-loader",
            options: {
              presets: ["@babel/preset-env", "@babel/preset-react"],
              plugins: [
                "@babel/plugin-transform-runtime",
                "@babel/plugin-transform-async-to-generator",
              ],
            },
          },
        ],
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"],
      },
    ],
  },
};
