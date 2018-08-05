cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
  {
    "id": "com.android.notification.notification",
    "file": "plugins/com.android.notification/www/notification.js",
    "pluginId": "com.android.notification",
    "clobbers": [
      "notification"
    ]
  }
];
module.exports.metadata = 
// TOP OF METADATA
{
  "com.android.notification": "0.7.0",
  "cordova-plugin-whitelist": "1.3.3"
};
// BOTTOM OF METADATA
});