/**
 * Created by kim on 2016-04-30.
 */
var express = require('express');

var app = express();

var isProduction = process.env.NODE_ENV === 'production';
var port = isProduction ? process.env.PORT : 3000;

// We point to our static assets
app.use(express.static('dist'));

// And run the server
app.listen(port, function () {
  console.log('Server running on port ' + port);
});
