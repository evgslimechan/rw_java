/// <reference path="jquery-3.4.1.min.js"/>

function closePanel(){
    if(token.length == 0){
        window.location.href = "login";
    }
    $("#add-panel").find("tr[name='idinfo']").hide();
    $("#add-panel").hide();
    $("#add-panel").find("input[type=text]").attr("value","");
    $("#add-panel").find("input[type=tel]").attr("value","");
    $("#add-panel").find("input[type=email]").attr("value","");
    $("#shadow").hide();
}
function openPanel(){
    if(token.length == 0){
        window.location.href = "login";
    }
    $("#add-panel").show();
    $("#shadow").show();
}
function add(){
    $("#add-panel").find("input[type=button]").attr("onclick", "tryAdd()");
    openPanel();
}
function edit(but){
    if(token.length == 0){
        window.location.href = "login";
    }
    var info = but.parentElement;

    var id = $(info).find("a[name='id']").get(0).innerHTML;
    var title = info.innerHTML.split("<p>")[0].trim();
    var text = info.innerHTML.split("<p>")[1].split("</p>")[0].trim();

    $("#add-panel").find("tr[name='idinfo']").show();

    $("#add-panel").find("td[name='id']").get(0).innerHTML = id;
    $("#add-panel").find("input[name='title']").attr("value", title);
    $("#add-panel").find("textarea[name='text']").get(0).innerHTML = text;

    $("#add-panel").find("input[type=button]").attr("onclick", "tryUpdate()");

    openPanel();
}

function tryAdd(){
    if(token.length == 0){
        window.location.href = "login";
    }
    var title = document.getElementsByName("title")[0];
    var text = document.getElementsByName("text")[0];
    var file = document.getElementsByName("file")[0];
    var $formData = new FormData();
    $formData.append("file", file.files[0]);
    $formData.append("title", title.value);
    $formData.append("text", text.value);
    $.ajax({
        url: "http://"+getURL()+"/api/news/add?token="+token,
        type: "POST",
        data: $formData,
        error: (data)=>{
            alert("ERROR "+data.toString());
        },
        success: (data)=>{
            alert("OK "+data.toString());
            closePanel();
            window.location.href = "news";
        },
        complete: ()=>{},
        processData: false,
        contentType: false
      });
}
function tryUpdate(){
    if(token.length == 0){
        window.location.href = "login";
    }
    var id = $("#add-panel").find("td[name='id']").get(0).innerHTML;
    var title = document.getElementsByName("title")[0];
    var text = document.getElementsByName("text")[0];
    var file = document.getElementsByName("file")[0];
    var $formData = new FormData();
    $formData.append("file", file.files[0]);
    $formData.append("title", title.value);
    $formData.append("text", text.value);
    $.ajax({
        url: "http://"+getURL()+"/api/news/edit?id="+id+"&token="+token,
        type: "POST",
        data: $formData,
        error: (data)=>{
            alert("ERROR "+data.toString());
        },
        success: (data)=>{
            closePanel();
            window.location.href = "news";
        },
        complete: ()=>{},
        processData: false,
        contentType: false
      });
}


function getURL(){
    return window.location.href.split("/")[2];
}