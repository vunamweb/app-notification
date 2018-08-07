/*global cordova, module*/

window.sum =function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Notification", "Notification", [name]);
};
