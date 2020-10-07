function showList(data) {
    var lis='';
    var list =  data.list;
    for (var i=0;i<list.length;i++){
        route=list[i]
        var li = ' <li>\n' +
            '                        <div class="img" ><img style="width: 299px;height: 169px" src="' + route.rimage + '" alt=""></div>\n' +
            '                        <div class="text1">\n' +
            '                            <p>' + route.rname + '</p>\n' +
            '                            <br/>\n' +
            '                            <p>' + route.routeIntroduce + '</p>\n' +
            '                        </div>\n' +
            '                        <div class="price">\n' +
            '                            <p class="price_num">\n' +
            '                                <span>&yen;</span>\n' +
            '                                <span>' + route.price + '</span>\n' +
            '                                <span>起</span>\n' +
            '                            </p>\n' +
            '                            <p><a href="route_detail.jsp">查看详情</a></p>\n' +
            '                        </div>\n' +
            '                    </li>'
        lis+=li;
    }
    $("#showlist").html(lis);


}
function showPageInfo(dataname,info,data) {
    console.log(data)
    dataname= "'"+dataname+"'";
    var totalCount = data.totalCount
    var totalPage = data.totalPage
    var currentPage = data.currentPage
    var pageSize = data.pageSize
    var pre = currentPage-1
    var next  = currentPage+1
    var start =10;
    var end =0;
    if(pre<=0){
        pre =1;
    }
    if(next>totalPage){
        next=totalPage;
    }
    if(totalPage<10){
        start=1
        end=totalPage;
    }else {
        start=currentPage-5
        end=currentPage+4
        if(start<=0){
            start=1
            end=start+9
        }
        if(end>totalPage){
            end=totalPage
            start=end-9
        }
    }

    $("#page_num_info").html('<i></i> 共\n' +
        '            <span>'+totalPage+'</span>页<span>'+totalCount+'</span>条')
    lis='                    <ul>\n' +
        '                        <li><a href="javascript:load('+dataname+',1,'+pageSize+')">首页</a></li>\n' +
        '                        <li class="threeword"><a href="javascript:load('+dataname+','+info+','+pre+','+pageSize+')">上一页</a></li>';

    for(var i =start;i<=end;i++){
        if(currentPage==i){
            lis+='<li class="curPage"><a href="javascript:load('+dataname+','+info+','+i+','+pageSize+')">'+i+'</a></li>'
        }else{
            lis+='<li><a href="javascript:load('+dataname+','+info+','+i+','+pageSize+')">'+i+'</a></li>'
        }

    }
    lis+='                        <li class="threeword"><a href="javascript:load('+dataname+','+info+','+next+','+pageSize+')">下一页</a></li>\n' +
        '                     <li class="threeword"><a href="javascript:load('+dataname+','+info+','+totalPage+','+pageSize+')">末页</a></li>\n' +
        '                    </ul>'
    $("#pageNum").html(lis)
    //滚动坐标
    window.scrollTo(0,500);


}