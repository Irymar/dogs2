const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {

    entry: './src/index.js', // Entry point of the application
    output: {
        path: path.resolve(__dirname, 'dist'), // Output directory
        filename: 'app.js' // Name of the output JS file
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html', // Path to the HTML template
        }),
        new CopyWebpackPlugin({
            patterns: [
                {
                    from: './src/style.css', // Path to CSS file
                    to: 'css/style.css' // Destination folder and file name
                }
            ]
        })
    ]
};
