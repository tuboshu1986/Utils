var ProductDics = {
    dics:{
        productState:{//产品状态
            codes:[1, 2, 3, 4, 5, 6, 7, 8, 9],
            names:["未提交", "已提交", "已退回", "待BOM审核", "待研发审核", "待发布", "被驳回", "已发布", "已停售"]
        },
        productInformation:{//产品生产料产品组成
            codes:['1', '2', '3', '4', '5', '100'],
            names:["PCB", "PCBA", "PCBA+外壳", "PCBA+外壳+包装", "外协产品", "其他"]
        },
        productBurnToolFrom:{//产品烧录置具方式
            codes:['1', '2', '3'],
            names:["不需要置具", "需要，工厂制作", "需要，客户提供"]
        },
        productBurnMethod:{//产品烧录方式
            codes:['1', '2'],
            names:["提前烧录", "在板烧录"]
        },
        productTestToolFrom:{//产品测试置具方式
            codes:['1', '2', '3'],
            names:["不需要置具", "需要，工厂制作", "需要，客户提供"]
        }
    },
    utils:{
        fromCode:function(code, type){
            var index = ProductDics.dics[type].codes.findIndex(function(item){
                return item === code;
            });
            if(index === 0 || index > 0){
                return ProductDics.dics[type].names[index];
            }
            return "";
        },
        fromName:function(name, type){
            var index = ProductDics.dics[type].names.findIndex(function(item){
                return item === name;
            });
            if(index === 0 || index > 0){
                return ProductDics.dics[type].codes[index];
            }
            return "";
        }
    }
};