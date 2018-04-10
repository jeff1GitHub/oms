(function ($) {
    $.extend({
        request: {
            /**
             * 向服务器发送post请求
             * @param url
             * @param data
             * @param success
             * @param error
             */
            post: function (url, data, success, error) {
                ajaxFunc(url, "POST", data, success, error);
            },

            /**
             * 向服务器发送get请求
             * @param url
             * @param data
             * @param success
             * @param error
             */
            get: function (url, data, success, error) {
                ajaxFunc(url, "GET", data, success, error);
            },

            /**
             * 向服务器发送put请求
             * @param url
             * @param data
             * @param success
             * @param error
             */
            put: function (url, data, success, error) {
                ajaxFunc(url, "PUT", data, success, error);
            },

            /**
             * 向服务器发送delete请求
             * @param url
             * @param data
             * @param success
             * @param error
             */
            del: function (url, data, success, error) {
                ajaxFunc(url, "DELETE", data, success, error);
            },
        }
    });
})(jQuery);

function ajaxFunc(url, method, data, success, error) {
    $.ajax({
        url: url,
        type: method,
        data: data,
        success: success,
        error: error
    });
}