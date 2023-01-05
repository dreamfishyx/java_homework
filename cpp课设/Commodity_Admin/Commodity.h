//
//Description：商品基类
//
#include <iostream>
using namespace std;
class Commodity {
private:
    string name;//名称
    double price;//价格
    string brand;//品牌
    int count;//库存
    string manufacturer;//生产厂家
    
        // static string category;//类别
    public:
        //getter方法:

        string getName();
        double getPrice();
        string getBrand();
        int getCount();
        string  getManufacturer();
        //setter方法[不包含类别]:

        void setName(string Name);
        void setPrice(double Price);
        void setBrand(string Brand);
        void setCount(int Count);
        void setManufacturer(string Manufacturer);
        //构造函数:

        Commodity();
        Commodity(string Name,double Price,string Brand,int Count,string Manufacturer);
        
        //打印函数
        void toString();
        //
        virtual int getUuid()=0;
        virtual string getCategory()=0;

    };
string Commodity::getName(){
    return name;
};
double Commodity::getPrice(){
    return price;
};
string Commodity::getBrand(){
    return brand;
};
int Commodity::getCount(){
    return count;
};
string Commodity::getManufacturer(){
    return manufacturer;
}

//setter方法[不包含类别]
void Commodity::setName(string Name){
    name=Name;
};
void Commodity::setPrice(double Price){
    price=Price;
};
void Commodity::setBrand(string Brand){
    brand=Brand;
};
void Commodity::setCount(int Count){
    count=Count;
};
void Commodity::setManufacturer(string Manufacturer){
        manufacturer=Manufacturer;
};


Commodity::Commodity(){
};
Commodity::Commodity(string Name,double Price,string Brand,int Count,string Manufacturer){
    name=Name;
    price=Price;
    brand=Brand;
    count=Count;
    manufacturer=Manufacturer;
};
 
void Commodity::toString(){
    cout<<"商品种类："<<getCategory()<<"\n商品名称："<<name<<"\n商品价格："<<price<<"\n商品品牌："<<brand<<"\n库存数量："<<count<<"\n生产厂家："<<manufacturer<<endl;
};