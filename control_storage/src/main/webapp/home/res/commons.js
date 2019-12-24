// 退出系统
function logout() {
    // 清空临时存储sessionStorage
    sessionStorage.clear();
    // 跳转到登录页面
    window.location.replace("login.html");

}
// 检查该用户是否存在
function checkEmpByUuid(requestUrl, uuid) {
    // 设置默认返回值是为false
    let flag = false;
    if (requestUrl != null && $.trim(requestUrl) != "" && uuid != null && $.trim(uuid) != "") {
        // 如果传入的uuid为不nullajax异步查询
        $.ajax({
            // 请求方式
            type: "post",
            // 被请求的url
            url: requestUrl + "/front/emp.action?methodName=checkEmpByUuid",
            // 返回值的数据类型
            dataType: "json",
            // 同步请求
            async: false,
            // 发送的数据 
            data: {
                uuid: uuid
            },
            success: (responseText) => {
                // 返回检查结果
                flag = responseText;
            },
            error: () => {
                // 返回默认值
                return flag;
            }
        });
    }
    // 返回检查结果
    return flag;
}