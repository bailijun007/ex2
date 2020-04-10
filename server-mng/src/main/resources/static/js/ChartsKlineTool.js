function buildKlineChart(id,title,dates,data,volumes,colorList) {
    var myChart = echarts.init(document.getElementById(id));
    var option = {
        animation: false,
        color: colorList,
        title: {
            left: 'left',
            text: title,
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        dataZoom: [
            {
                type: 'inside',
                start: 50,
                end: 100
            },
            {
                show: true,
                type: 'slider',
                top: '90%',
                start: 50,
                end: 100
            }
        ],
        xAxis: [{
            type: 'category',
            data: dates,
            boundaryGap : false,
            axisLine: { lineStyle: { color: '#777' } },
            axisLabel: {
                formatter: function (value) {
                    return echarts.format.formatTime('MM-dd hh:mm', value);
                }
            },
            min: 'dataMin',
            max: 'dataMax',
            axisPointer: {
                show: true
            }
        }, {
            type: 'category',
            gridIndex: 1,
            data: dates,
            scale: true,
            boundaryGap : false,
            splitLine: {show: false},
            axisLabel: {show: false},
            axisTick: {show: false},
            axisLine: { lineStyle: { color: '#777' } },
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax',
            axisPointer: {
                type: 'shadow',
                label: {show: false},
                triggerTooltip: true,
                handle: {
                    show: true,
                    margin: 30,
                    color: '#B80C00'
                }
            }
        }],
        yAxis: [{
            scale: true,
            splitNumber: 2,
            axisLine: { lineStyle: { color: '#777' } },
            splitLine: { show: true },
            axisTick: { show: false },
            axisLabel: {
                inside: true,
                formatter: '{value}\n'
            }
        }, {
            scale: true,
            gridIndex: 1,
            splitNumber: 2,
            axisLabel: {show: false},
            axisLine: {show: false},
            axisTick: {show: false},
            splitLine: {show: false}
        }],
        grid: [{
            left: 20,
            right: 20,
            top: 50,
            height: 200
        }, {
            left: 20,
            right: 20,
            height: 40,
            top: 280
        }],
        series: [{
            name: 'Volume',
            type: 'bar',
            xAxisIndex: 1,
            yAxisIndex: 1,
            itemStyle: {
                color: '#7fbe9e'
            },
            data: volumes
        }, {
            type: 'candlestick',
            name: 'K线',
            data: data,
            itemStyle: {
                color: '#ef232a',
                color0: '#14b143',
                borderColor: '#ef232a',
                borderColor0: '#14b143'
            },
            //emphasis: {
                //itemStyle: {
                    //color: 'black',
                    //color0: '#444',
                    borderColor: 'black'
                    //borderColor0: '#444'
                //}
           // }
        },
           /* {
                name: 'volume',
                type: 'line',
                data: calculateMA(volumes),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    width: 1
                }
            }*/
        ]
    };
    myChart.setOption(option);
    window.onresize = myChart.resize;
}

/*function calculateMA(data) {
    var result = [];
    for (var i = 0, len = data.length; i < len; i++) {
        result.push(data[i]);
    }
    return result;
}*/
