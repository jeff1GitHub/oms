(function ($) {
    $.extend({
        myTime: {
            /**
             * 当前时间戳
             *
             * @return <int> unix时间戳(毫秒)
             */
            CurTime: function () {
                return Date.parse(new Date());
            },

            /**
             * 日期 转换为 Unix时间戳
             *
             * @param <string> 2014-01-01 20:20:20 日期格式
             * @return <int> unix时间戳(毫秒)
             */
            DateToUnix: function (string) {
                var f = string.split(' ', 2);
                var d = (f[0] ? f[0] : '').split('-', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(parseInt(d[0], 10) || null, (parseInt(
                    d[1], 10) || 1) - 1,
                    parseInt(d[2], 10) || null, parseInt(t[0], 10)
                    || null, parseInt(t[1], 10) || null,
                    parseInt(t[2], 10) || null)).getTime();
            },

            /**
             * 北京时间转换时间戳
             * @return <int> unix时间戳(毫秒)
             */
            BeijingDateToUnix: function (string) {
                var f = string.split(' ', 2);
                var d = (f[0] ? f[0] : '').split('-', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(parseInt(d[0], 10) || null, (parseInt(
                    d[1], 10) || 1) - 1,
                    parseInt(d[2], 10) || null, parseInt(t[0], 10)
                    || null, parseInt(t[1], 10) || null,
                    parseInt(t[2], 10) || null)).getTime();
            },

            /**
             * 时间戳转换日期
             *
             * @param <int> unixTime 待时间戳(秒)
             * @param <bool> isFull 返回完整时间(Y-m-d 或者 Y-m-d H:i:s)
             * @param <int> timeZone 时区
             */
            UnixToDate: function (unixTime, isFull, timeZone) {
                if (typeof (timeZone) == 'number') {
                    unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
                }
                var time = new Date(unixTime);
                var ymdhis = "";
                ymdhis += time.getUTCFullYear() + "-";
                ymdhis += format((time.getUTCMonth() + 1)) + "-";
                ymdhis += format(time.getUTCDate());
                if (isFull === true) {
                    ymdhis += " " + format(time.getUTCHours()) + ":";
                    ymdhis += format(time.getUTCMinutes()) + ":";
                    ymdhis += format(time.getUTCSeconds());
                }
                return ymdhis;
            },

            /**
             * @param <int> unixTime 待时间戳(毫秒)
             */
            UnixToBeijingDate: function (unixTime) {
                if (unixTime == null || unixTime == "") {
                    return "";
                }
                unixTime = parseInt(unixTime) + 8 * 60 * 60 * 1000;// 加上北京时区的8个小时
                var time = new Date(unixTime);
                var ymdhis = "";
                ymdhis += time.getUTCFullYear() + "-";
                ymdhis += format((time.getUTCMonth() + 1)) + "-";
                ymdhis += format(time.getUTCDate());
                ymdhis += " "
                ymdhis += format(time.getUTCHours()) + ":";
                ymdhis += format(time.getUTCMinutes()) + ":";
                ymdhis += format(time.getUTCSeconds());
                return ymdhis;
            },

            /**
             * 获取当前日期
             */
            getToDay: function () {
                var unixTime = $.myTime.CurTime();
                return $.myTime.UnixToDate(unixTime, false, 8);
            },

            /**
             * 获取当前日期的前7天
             */
            get7Day: function () {
                var interval = 7;

                var unixTime = $.myTime.CurTime();
                unixTime = parseInt(unixTime) + 8 * 60 * 60 * 1000;// 加上北京时区的8个小时
                var time = new Date(unixTime);
                var ymdhis = "";

                var year = time.getUTCFullYear();
                var month = time.getUTCMonth() + 1;
                var day = time.getUTCDate() - interval

                // 如果天数小于或是等于0表示需要到上一个月的某天
                if (day <= 0) {
                    // 将月份减1表示上个月
                    month -= 1;
                    if (month <= 0) {
                        year -= 1;
                        month = 12 + month;
                    }
                    // 获取上月的天数
                    monthDay = $.myTime.getMonthDay(month);
                    // 把多扣除的天数扣除在上个月
                    day = monthDay + day;
                }
//				ymdhis += time.getUTCFullYear() + "-";
//				ymdhis += format((time.getUTCMonth() + 1)) + "-";
//				ymdhis += format(time.getUTCDate() - 7);
                ymdhis = year + '-' + format(month) + '-' + format(day);
                return ymdhis;
            },

            /**
             * 获取当前日期的前1个月
             */
            get1Month: function () {
                return $.myTime.getMonthInterval(1);
            },

            /**
             * 获取当前日期的前3个月
             */
            get3Month: function () {
                return $.myTime.getMonthInterval(3);
            },

            getMonthInterval: function (num) {
                var unixTime = $.myTime.CurTime();
                unixTime = parseInt(unixTime) + 8 * 60 * 60 * 1000;// 加上北京时区的8个小时
                var time = new Date(unixTime);

                var year = time.getUTCFullYear();
                var month = time.getUTCMonth() + 1 - num;// 加1是因为系统1月为0,减1是因为需要的是上个月的时间
                var day = time.getUTCDate();

                if (month <= 0) {
                    year -= 1;
                    month = 12 + month;
                }

                var ymdhis = year + '-' + format(month) + '-' + format(day);
//				ymdhis += time.getUTCFullYear() + "-";
//				ymdhis += format((time.getUTCMonth() + 1 - 1)) + "-";
//				ymdhis += format(time.getUTCDate() + 7);
                return ymdhis;
            },

            /**
             * 获取每个月的天数
             */
            getMonthDay: function (month) {
                switch (month) {
                    case 1:
                        return 31;
                    case 2:
                        return 28;
                    case 3:
                        return 31;
                    case 4:
                        return 30;
                    case 5:
                        return 31;
                    case 6:
                        return 30;
                    case 7:
                        return 31;
                    case 8:
                        return 31;
                    case 9:
                        return 30;
                    case 10:
                        return 31;
                    case 11:
                        return 30;
                    case 12:
                        return 31;
                    default:
                        break;
                }
            }
        }
    });
})(jQuery);

/**
 * 返回的时间格式可能只有个位数,当只有增加十位的0
 *
 * @param time
 * @returns
 */
function format(time) {
    time = time + "";
    if (time.length == 1) {
        return "0" + time;
    }
    return time;
}


/**
 * 初始化时间控件
 *
 * @param element
 * @returns
 */
function initDaterangepicker(element) {
    // Date range picker with time picker
    $('#' + element).daterangepicker({
        minDate: '2017/06/01',// 最小时间
        timePicker: true,
        timePickerIncrement: 30,
        startDate: "2017-07-01",
        endDate: moment() + 1,
        locale: {
            startDate: false,
            endDate: false,
            format: 'YYYY-MM-DD HH:mm:SS',
            applyLabel: '确定',
            cancelLabel: '取消'
        }
    });
};

/**
 * 控件字符串转换时间戳
 *
 * @param elementName
 * @param isStart
 * @returns
 */
function getUnixByDaterangepicker(elementName, isStart) {
    var time = $("#" + elementName).val();
    if (time == "") {
        return;
    }
    if (isStart) {
        time = time.substring(0, 19);
    } else {
        time = time.substring(22, 42);
    }
    return $.myTime.BeijingDateToUnix(time)
}
