
$(document).ajaxSend(function (e,xhr,opt){
    var user_token = localStorage.getItem("user_token");
    console.log("要发送的:"+user_token);
    xhr.setRequestHeader("user_token",user_token);
});


function getUserInfo(userUrl){
    console.log("aaa");
    $.ajax({
        type:"get",
        url:userUrl,
        success:function(result){
            if(result.code == 200 && result.data != null){
                console.log("bbb");
                $(".left .card h3").text(result.data.userName);
                $(".left .card a").attr("href",result.data.githubUrl);
                console.log(result.data.githubUrl);
            }
        },
        error:function( ){
            //console.log("aaa");
        }
    });
}
    function logout(){
        //删除cookie，设置cookie为空即可
        localStorage.removeItem("user_token");
        location.href = "/blog_login.html";
    }


