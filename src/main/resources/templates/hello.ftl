<html>
<body>
welcome ${name}  to freemarker!${request.contextPath}

<table>
    <#list list as p>
    <tr>
        <td>${p_index+1}个对象============${p.userId}--${p.blance}</td>
    </tr>
    </#list>
</table>
${pageInfo.pageNum},
${pageInfo.pageSize},
${pageInfo.total}
</body>
</html>