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
    if(token.length===0){
        window.location.href = "login";
    }
    $("#add-panel").show();
    $("#shadow").show();
}

function edit(but){
    if(token.length == 0){
        window.location.href = "login";
    }
    var info = but.parentElement;

    var id = $(info).find("a[name='id']").get(0).innerHTML;
    var name = info.innerHTML.split("<p>")[0].trim();
    var desc = info.innerHTML.split("<p>")[1].split("</p>")[0].trim();

    $("#add-panel").find("tr[name='idinfo']").show();

    $("#add-panel").find("td[name='id']").get(0).innerHTML = id;
    $("#add-panel").find("input[name='name']").attr("value", name);
    $("#add-panel").find("textarea[name='desc']").get(0).innerHTML = desc;
    $("#add-panel").find("input[type=button]").attr("onclick", "tryEdit(this)");

    openPanel();
}
function add(){
    $("#add-panel").find("td[name='id']").get(0).innerHTML = "<input type='text' name='id'/>";
    $("#add-panel").find("input[type=button]").attr("onclick", "tryAdd(this)");
    openPanel();
}
function tryAdd(button) {

    var name = document.getElementsByName("name")[0];
    var desc = document.getElementsByName("desc")[0];
    var cfg = document.getElementsByName("config")[0];
    cfg = cfg.options[cfg.selectedIndex].text;
    var $formData = new FormData();
    $formData.append("name", name.value);
    $formData.append("desc", desc.value);
    $formData.append("config", cfg);

    $.ajax({
        url: "http://"+getURL()+"/api/track/add?token="+token,
        type: "POST",
        data: $formData,
        error: (data)=>{
            alert("ERROR "+data.toString());
        },
        success: (data)=>{
            window.location.href = "tracks";
            alert("OK "+data.toString());
        },
        complete: ()=>{},
        processData: false,
        contentType: false
    });
}
function tryRemove(button){
    if(token.length === 0){
        window.location.href = "login";
    }
    var id = $(button.parentElement.parentElement).find("a[name='id']").get(0).innerHTML;
    $.ajax({
        url: "http://"+getURL()+"/api/track/remove?id="+id+"&token="+token,
        type: "GET",
        data: "",
        error: (data)=>{
            alert("ERROR "+data.toString());
        },
        success: (data)=>{
            closePanel();
            window.location.href = "tracks";
        },
        complete: ()=>{},
        processData: false,
        contentType: false
    });
}
function tryEdit(button){
    if(token.length == 0){
        window.location.href = "login";
    }

    var id = document.getElementsByName("idinfo")[0].childNodes[1].innerHTML;
    var name = document.getElementsByName("name")[0];
    var desc = document.getElementsByName("desc")[0];
    var cfg = document.getElementsByName("config")[0];
    cfg = cfg.options[cfg.selectedIndex].text;
    var $formData = new FormData();
    $formData.append("id", id);
    $formData.append("name", name.value);
    $formData.append("desc", desc.value);
    $formData.append("cfg", cfg);

    $.ajax({
        url: "http://"+getURL()+"/api/track/edit?token="+token,
        type: "POST",
        data: $formData,
        error: (data)=>{
            alert("ERROR "+data.toString());
        },
        success: (data)=>{
            window.location.href = "tracks";
        },
        complete: ()=>{},
        processData: false,
        contentType: false
    });
}
function getURL(){
    return window.location.href.split("/")[2];
}