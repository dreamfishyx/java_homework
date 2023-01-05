//
//Description:定义交互界面
//
#include "Admin.h"
Admin adm=Admin();

//统计模式
void show(){
    cout<<"请选择统计模式："<<endl;
    cout<<"  1.对价格统计\n"<<"  2.对库存统计"<<endl;
    int i=0;
    cout<<">>> ";
    cin>>i;
    switch(i){
        case 1:
            adm.show(true);
            break;
        default:
            adm.show(false);
            break;
    }
}
//添加模式
void add(){
    int kind=0,count=0;
    string name="",brand="",manufacturer="";
    double price=0;
    bool ans=true;
    cout<<"选择添加商品种类：\n"<<"1.食品 2.饮料 3.化妆品 4.日用品"<<endl;
    cout<<">>> ";
    cin>>kind;
    cout<<"请输入商品名称：\n"<<">>> ";
    cin>>name;
    cout<<"请输入商品价格：\n"<<">>> ";
    cin>>price;
    cout<<"请输入商品库存：\n"<<">>> ";
    cin>>count;
    cout<<"请输入商品品牌：\n"<<">>> ";
    cin>>brand;
    cout<<"请输入商品厂家：\n"<<">>> ";
    cin>>manufacturer;
    switch (kind)
        {
        case 1:
            ans=adm.add(new Food(name,price,brand,count,manufacturer));
            break;
        case 2:
             ans=adm.add(new Drink(name,price,brand,count,manufacturer));
            break;
        case 3:
             ans=adm.add(new Cosmetic(name,price,brand,count,manufacturer));
            break;
        case 4:
             ans=adm.add(new Daily(name,price,brand,count,manufacturer));
            break;
        default:
            break;
        }
        if(ans)
            cout<<"添加成功！"<<endl;
}
//购买模式
void buy(){
    string name="";
    int uuid=0,sum=0,kind=0;
     cout<<"选择购买商品种类：\n"<<"1.食品 2.饮料 3.化妆品 4.日用品"<<endl;
    cout<<">>> ";
    cin>>kind;
    cout<<"输入购买商品名称：\n"<<">>> ";
    cin>>name;
    switch (kind)//判断uuid
    {
    case 1:
        uuid=Food::uuid;
        break;
    case 2:
        uuid=Drink::uuid;
        break;
    case 3:
        uuid=Cosmetic::uuid;
        break;
    case 4:
        uuid=Daily::uuid;
        break;
    default:
        break;
    }
    int index=adm.search(name,uuid);
    if(index==-1){
        cout<<"抱歉，未找到您要的商品！"<<endl;
    }else{
        Commodity* c =adm.get(index);
        c->toString();
        cout<<"请输入购买数量：\n"<<">>> ";
        cin>>sum;
       if(adm.buy(index,sum)){
        cout<<"购买成功！"<<endl;
       }
    }
}
//查找
void search(){
    int kind=0,uuid=0;
    string name="",manufacturer="";
    cout<<"选择查询模式：\n"<<"  1.按种类\n"<<"  2.按名称\n"<<"  3.按生产厂家\n"<<">>> ";
    cin>>kind;
    switch (kind)
    {
    case 1:
        cout<<"选择搜索商品种类：\n"<<"1.食品 2.饮料 3.化妆品 4.日用品"<<endl;
        cout<<">>> ";
        cin>>kind;
        switch (kind)//判断uuid
        {
        case 1:
            uuid=Food::uuid;
            break;
        case 2:
            uuid=Drink::uuid;
            break;
        case 3:
            uuid=Cosmetic::uuid;
            break;
        case 4:
            uuid=Daily::uuid;
            break;
        default:
            break;
        }
        adm.printByUuid(uuid);
        break;
    case 2:
        cout<<"输入商品名称：\n"<<">>> ";
        cin>>name;
        adm.printByName(name);
        break;
    case 3:
        cout<<"输入生产公司：\n"<<">>> ";
        cin>>manufacturer;
        adm.printByManufacturer(manufacturer);
        break;
    default:
        break;
    }
}
//删除
void drop(){
    int kind=0,uuid=0;
    string name="";
     cout<<"选择删除商品种类：\n"<<"1.食品 2.饮料 3.化妆品 4.日用品"<<endl;
    cout<<">>> ";
    cin>>kind;
    cout<<"输入删除商品名称：\n"<<">>> ";
    cin>>name;
    switch (kind)//判断uuid
    {
    case 1:
        uuid=Food::uuid;
        break;
    case 2:
        uuid=Drink::uuid;
        break;
    case 3:
        uuid=Cosmetic::uuid;
        break;
    case 4:
        uuid=Daily::uuid;
        break;
    default:
        break;
    }
    int index=adm.search(name,uuid);
    // cout<<"index:"<<index<<endl;
    if(index==-1){
        cout<<"没有找到要删除的商品！"<<endl;
    }else{
        if(adm.drop(index))
            cout<<"删除成功！"<<endl;
    }
}
//修改
void update(){
    int kind=0,count=0,uuid=0;
    string name="";
    Commodity* com;
    double price=0;
    cout<<"选择修改商品种类：\n"<<"1.食品 2.饮料 3.化妆品 4.日用品"<<endl;
    cout<<">>> ";
    cin>>kind;
    cout<<"输入商品名称：\n"<<">>> ";
    cin>>name;
    switch (kind)//判断uuid
    {
    case 1:
        uuid=Food::uuid;
        com=new Food();
        break;
    case 2:
        uuid=Drink::uuid;
        com=new Drink();
        break;
    case 3:
        uuid=Cosmetic::uuid;
        com=new Cosmetic();
        break;
    case 4:
        uuid=Daily::uuid;
        com=new Daily();
        break;
    default:
        break;
    }
    int index=adm.search(name,uuid);
    if(index==-1){
        cout<<"没有找到要修改的商品！"<<endl;
    }else{
        cout<<"请选择修改类型：\n"<<"  1.修改价格\n"<<"  2.修改库存\n"<<">>> ";
        int i=0;
        cin>>i;
        switch (i)
        {
        case 1:
            cout<<"请输入价格：\n"<<">>> ";
            cin>>price;
            com->setPrice(price);
            if(adm.update(com,index,true))
                cout<<"修改成功！"<<endl;
            break;
        default:
            cout<<"请输入库存：\n"<<">>> ";
            cin>>count;
            com->setCount(count);
            if(adm.update(com,index,false))
                cout<<"修改成功！"<<endl;
            break;
        }
        
    }
}
//菜单
void myMenu(){
cout<<"********************************************************"<<endl;
cout<<"\t--------欢迎进入超市商品管理系统--------"<<endl;
cout<<"\t********\t1.购买商品\t********"<<endl;
cout<<"\t********\t2.添加商品\t********"<<endl;
cout<<"\t********\t3.删除商品\t********"<<endl;
cout<<"\t********\t4.修改商品\t********"<<endl;
cout<<"\t********\t5.查询商品\t********"<<endl;
cout<<"\t********\t6.统计商品\t********"<<endl;
cout<<"\t********\t0.退出保存\t********"<<endl;
cout<<"********************************************************"<<endl;
}
