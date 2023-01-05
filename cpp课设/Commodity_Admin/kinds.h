//
//Description：对商品子类的定义
//
#include "Commodity.h"

//食品类
class Food:public Commodity
{
    public:
        static const string category;//类别
        static const int uuid;//switch不支持string
        Food(){
        };
        Food(string Name,double Price,string Brand,int Count,string Manufacturer):Commodity(Name, Price, Brand, Count, Manufacturer){
        }
        int getUuid() override;//重写获取uuid方法category
        string getCategory() override;//重写获取category方法
};
const string Food::category="食品";
const int Food::uuid=123;
int Food::getUuid(){
    return uuid;
}
string Food::getCategory(){
    return category;
}


//饮料类
class Drink:public Commodity
{
    public:
        static const string category;//类别
        static const int uuid;//switch不支持string

        Drink(){
        };
        Drink(string Name,double Price,string Brand,int Count,string Manufacturer):Commodity(Name, Price, Brand, Count, Manufacturer){
        }
        int getUuid() override;
        string getCategory() override;
};
const string Drink::category="饮料";
const int Drink::uuid=456;
int Drink::getUuid(){
    return uuid;
}
string Drink::getCategory(){
    return category;
}

//化妆品类
class Cosmetic:public Commodity
{
    public:
        static const string category;//类别
        static const int uuid;//switch不支持string

        Cosmetic(){
        };
        Cosmetic(string Name,double Price,string Brand,int Count,string Manufacturer):Commodity(Name, Price, Brand, Count, Manufacturer){
        }
        int getUuid() override;
        string getCategory() override;
};
const string Cosmetic::category="化妆品";
const int Cosmetic::uuid=789;
int Cosmetic::getUuid(){
    return uuid;
}
string Cosmetic::getCategory(){
    return category;
}

//日用品类
class Daily:public Commodity
{
    public:
        static const string category;//类别
        static const int uuid;//switch不支持string

        Daily(){
        };
        Daily(string Name,double Price,string Brand,int Count,string Manufacturer):Commodity(Name, Price, Brand, Count, Manufacturer){
        }
        int getUuid() override;
        string getCategory() override;
};
const string Daily::category="日用品";
const int Daily::uuid=101;
int Daily::getUuid(){
    return uuid;
}
string Daily::getCategory(){
    return category;
}