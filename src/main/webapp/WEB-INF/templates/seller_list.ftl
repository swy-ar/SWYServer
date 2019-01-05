<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>三维云</title>

    <style type="text/css">
        body {
            font-family: 微软雅黑;
        }

        body {
            font-size: 14px;
        }

        table {
            font-size: 14px;
        }

        table {
            border-collapse: collapse;
            border: none;
            cellspacing: 30px;
        }

        td {
            border: solid 1px #bddddd;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            height: 30px;
        }

        .td-id {
            width: 40px;
            max-width: 40px;
            text-align: center;
        }

        .td-name {
            width: 120px;
            max-width: 120px;
        }

        .td-desc {
            width: 150px;
            max-width: 150px;
        }

        .td-time {
            width: 100px;
            max-width: 100px;
        }

        .td-status {
            width: 80px;
            max-width: 80px;
            text-align: center;
        }

        .td-oper {
            width: 80px;
            max-width: 80px;
            text-align: center;
        }

        .button-add {
            -webkit-appearance: button;
            width: 100px;
            height: 22px;
            float: right;
            margin-top: -10px;
            margin-bottom: 10px;
        }
        .dialog-title {
            text-align: center;
            font-weight:bold;
            font-size: 15px;
        }

        .body-footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 30px;
        }
    </style>

    <!-- jquery -->
    <script type="text/javascript" src="../assets/jquery-2.2.4.js"></script>

    <script type="text/javascript">
        function openDialog() {
            document.getElementById("add-dialog").show();
        }
        function checkAndSend() {
            document.getElementById("addForm").submit();
        }
        function closeDialog() {
            document.getElementById("add-dialog").close();
        }
        function showResult(result) {
            if ('success' == result) {
                location.reload(true);
            } else {
                alert(result);
            }
        }
        function toType() {
            window.open("../seller/type_list?");
        }
        function toBrand() {
            window.open("../seller/brand_list?");
        }
        function toOneSeller(sellerId) {
            location.href = "../seller/info?sId=" + sellerId;
        }
        function deleteSeller(sellerId) {
            var r = confirm("是否确认删除该商家?\n\n提示: 删除该商家后,该商家下所有的模型也将会被删除");
            if (r == true) {
                // 发起ajax请求
                var url = "../api/deleteSeller?sellerId=" + sellerId;
                var htmlobj = $.ajax({url:url, async:false});
                var respJson = JSON.parse(htmlobj.responseText);
                if (respJson.code == 200) {
                    location.reload(true);
                    alert("删除成功");
                } else {
                    alert(htmlobj.responseText);
                }
            }
        }
    </script>

</head>

<body>

<h1>接入商家列表</h1>

<iframe name="handleFrame" id="handleFrame" style="display:none"></iframe>

<div>
    <input type="button" value="增加商户" onclick="openDialog();" class="button-add">
    <dialog close id="add-dialog" name="add-dialog">
        <p class="dialog-title">添加商家</p>
        <form id="addForm" name="addForm" enctype="multipart/form-data" method="post" target="handleFrame" action="../api/addSeller">
            <p>商家名称: <input name="sellerName" type="text" value=""/></p>
            <p>商家简介: <input name="sellerDesc" type="text" value=""/></p>
            <p>商家地址: <input name="address" type="text" value=""/></p>
            <p>联系电话: <input name="mobile" type="text" value=""/></p>
            <p>商家网站: <input name="website" type="text" value="">(可选)</p>
            <p>
                <input type="button" value="确定" onclick="checkAndSend();" style="width:120px;height:60px;"/>
                <input type="button" value="取消" onclick="closeDialog();" style="width:120px;height:60px;"/>
            </p>
        </form>
    </dialog>
    </input>
    <table id="msgTable" name="msgTable" width="100%">
        <tr>
            <th class="td-id">商家id</th>
            <th class="td-name">商家名称</th>
            <th class="td-desc">描述</th>
            <th class="td-time">创建时间</th>
            <th class="td-status">是否禁用</th>
            <th class="td-oper">操作</th>
        </tr>
    <#if list?exists>
        <#list list as item>
            <tr>
                <td class="td-id">${item["sellerId"]}</td>
                <td class="td-name">${item["sellerName"]}</td>
                <td class="td-desc">${item["sellerDesc"]}</td>
                <td class="td-time">${item["createTime"]}</td>
                <td class="td-status">
                    <#if item.isDisabel==false>
                        正常
                    <#else>
                        禁用
                    </#if>
                </td>
                <td class="td-oper">
                    <a href="#" onclick="toOneSeller(${item["sellerId"]});">查看</a>
                    <a href="#" onclick="deleteSeller(${item["sellerId"]});">删除</a>
                </td>
            </tr>
        </#list>
    </#if>
    </table>
</div>

<#--<div class="body-footer">-->
    <#--<button onclick="toType();" style="float:left;">分类管理</button>-->
    <#--<button onclick="toBrand();" style="float:right; margin-right:15px;">品牌管理</button>-->
<#--</div>-->

</body>

</html>