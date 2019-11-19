function buildLineChart(id,title,xData,yData,colorList)
{
    // var colorList = [
    //     '#C1232B', '#C1232B', '#FCCE10', '#C1232B', '#C1232B',
    //     '#C1232B', '#C1232B', '#C1232B', '#C1232B', '#C1232B',
    //     '#C1232B', '#C1232B'
    // ];
    var barChart = echarts.init(document.getElementById(id));
    var baroption = {
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis'
        },
        // legend: {
        //     data:['蒸发量','降水量'] //这玩意标识头上有几个标志位
        // },
        grid: {  //这万一控制滚动条距离X轴高度的
            x: 40,
            x2: 2
        },
        dataZoom: {  //这玩意显示滚动条的
            show: true,
            realtime: true,
            start: 0,  //滚动条百分比
            end: 100
        },
        // calculable : true,
        xAxis: [
            {
                type: 'category',
                // boundaryGap : true,
                data: xData//['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [  //这个里头有几个{}就有几个柱子的数据
            // {
            //     name:'蒸发量',
            //     type:'bar',
            //     data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            //     // markPoint : {   画点
            //     //     data : [
            //     //         {type : 'max', name: '最大值'},
            //     //         {type : 'min', name: '最小值'}
            //     //     ]
            //     // },
            //     // markLine : {  画线
            //     //     data : [
            //     //         {type : 'average', name: '平均值'}
            //     //     ]
            //     // }
            // },
            {
                name: 'K线条数',
                type: 'bar',
                data: yData,//[100, 35.5, 36.4, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 76.6, 85],
                itemStyle: {
                    //通常情况下：
                    normal: {
                        // 每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                        color: function (params) {
                            console.log(params);
                            return colorList[params.dataIndex];
                        }
                    }
                }
                // markPoint : {
                //     data : [
                //         {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                //         {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                //     ]
                // },
                // markLine : {
                //     data : [
                //         {type : 'average', name : '平均值'}
                //     ]
                // }
            }

        ]
    };
    barChart.setOption(baroption);

    window.onresize = barChart.resize;
}