//
//Description：商品信息管理
//
#include "kinds.h"
#include <fstream>
// #include <filesystem>

const int maxsize=100;//最大商品种类
const string filePath="info.txt";//存储文件位置

class Admin{
    private://四种
        Commodity *com[maxsize];
        int length;
    public:
        Admin();
        //文件读取
        bool read();
        //文件写入
        bool write();
        //添加商品
        bool add(Commodity*c);
        //展示商品[true:展示价格，false：展示库存]
        void show(bool model);
        //删除
        bool drop(int index);
        //修改[true:修改价格，false：修改库存]
        bool update(Commodity*c,int index,bool model);
        //购买
        bool buy(int index,int num);
        //查询[-1表示未找到]
        int search(string name,int uuid);
        //通过索引获取
        Commodity* get(int index);
        //通过name查询
        void printByName(string name);
        void printByUuid(int uuid);
        void printByManufacturer(string manufacturer);
};
Admin::Admin():length(0){};
bool Admin::write(){
    int index=length-1;
    
    ofstream fout;
    fout.open("info.txt",ios::out);
    while(index!=-1){
        fout<<com[index]->getName()<<' '<<com[index]->getPrice()<<' '<<com[index]->getBrand()<<' '<<com[index]->getCount()<<' '<<com[index]->getManufacturer()<<' '<<com[index]->getUuid()<<endl;
        index--;
    }
    fout.close();
    return true;
}
bool Admin::read(){
    string name={"name"},brand={"brand"},manufacturer={"manufacturer"};
    double price=0.0;
    int count=0,uuid=0;
 
    ifstream fin;
    fin.open("info.txt",ios::in);
    //读取数据
    while (!fin.eof())
    {   uuid=0;//读取时总是把最后空行读入，很麻烦，选用uuid判断是否为空行。
        fin>>name>>price>>brand>>count>>manufacturer>>uuid;
        switch (uuid)//吐了，switch不支持string
        {
        case Food::uuid:
            com[length]=new Food(name,price,brand,count,manufacturer);
            length++;
            break;
        case Drink::uuid:
            com[length]=new Drink(name,price,brand,count,manufacturer);
            length++;
            break;
        case Cosmetic::uuid:
            com[length]=new Cosmetic(name,price,brand,count,manufacturer);
            length++;
            break;
        case Daily::uuid:
            com[length]=new Daily(name,price,brand,count,manufacturer);
            length++;
            break;
        default:
            break;
        }
        //length++ 吐了，空文件也能读取
        // cout<<"读取时length:"<<length<<endl;
    }
    fin.close();
    return true;  
}
bool Admin::add(Commodity*c){
    if(length<maxsize){
        com[length]=c;
        length++;
        // cout<<"添加length："<<length<<endl;
        return true;
    }else{
        cout<<"商品类型已满！"<<endl;
        return false;
    }

}
void Admin::show(bool model){
    if(model){
        cout<<"名称："<<"      \t\t"<<"价格："<<endl;
    }else{
        cout<<"名称："<<"      \t\t"<<"库存："<<endl;
    }
    int index=length-1;
    while (index>-1)
    {
        if(model){
            cout<<com[index]->getName()<<"      \t\t"<<com[index]->getPrice()<<endl;
        }else{
            cout<<com[index]->getName()<<"      \t\t"<<com[index]->getCount()<<endl;
        }
        index--;
    }
    
}
bool Admin::drop(int index){
    for(int i=index+1;i<length;i++){  //将要删除位置后面数据前移
        com[i-1]=com[i];
    }
    length--;   //数据个数减一
    return true;
}
        
bool Admin::update(Commodity*c,int index,bool model){
    if(model){
        com[index]->setPrice(c->getPrice());
    }else{
        com[index]->setCount(c->getCount());
    }
    return true;
};

Commodity* Admin::get(int index){
    return com[index];
}      
bool Admin::buy(int index,int num){
    if(com[index]->getCount()<num){
        cout<<"购买失败，库存不足！"<<endl;
        return false;
    }else{
        com[index]->setCount(com[index]->getCount()-num);
    }
    return true;
}
        
int Admin::search(string name,int uuid){
    int index=length-1;
    while(index>-1){
        if(com[index]->getName()==name&&com[index]->getUuid()==uuid){//判断是否满足条件
            // com[index]->toString();
            return index;
        }
        index--;
    }
    return index;//-1
}
void Admin::printByName(string name){
    int index=length-1,count=0;
    cout<<endl;
    while(index>-1){
        if(com[index]->getName()==name){//判断是否满足条件
             com[index]->toString();
            cout<<"--------------------"<<endl;
            count++;
        }
        index--;
    }
   if(count==0){
        cout<<"未查询到相关记录"<<endl;
    }
};
void Admin::printByUuid(int uuid){
 int index=length-1,count=0;
    cout<<endl;
    while(index>-1){
        if(com[index]->getUuid()==uuid){//判断是否满足条件
             com[index]->toString();
             cout<<"--------------------"<<endl;
             count++;
        }
        index--;
    }
    if(count==0){
        cout<<"未查询到相关记录"<<endl;
    }
}
void Admin::printByManufacturer(string manufacturer){
    int index=length-1,count=0;
    cout<<endl;
    while(index>-1){
        if(com[index]->getManufacturer()==manufacturer){//判断是否满足条件
             com[index]->toString();
             cout<<"--------------------"<<endl;
             count++;
        }
        index--;
    }
    if(count==0){
        cout<<"未查询到相关记录"<<endl;
    }
}
