//
//Description������Ʒ����Ķ���
//
#include "Commodity.h"

//ʳƷ��
class Food:public Commodity
{
    public:
        static const string category;//���
        static const int uuid;//switch��֧��string
        Food(){
        };
        Food(string Name,double Price,string Brand,int Count,string Manufacturer):Commodity(Name, Price, Brand, Count, Manufacturer){
        }
        int getUuid() override;//��д��ȡuuid����category
        string getCategory() override;//��д��ȡcategory����
};
const string Food::category="ʳƷ";
const int Food::uuid=123;
int Food::getUuid(){
    return uuid;
}
string Food::getCategory(){
    return category;
}


//������
class Drink:public Commodity
{
    public:
        static const string category;//���
        static const int uuid;//switch��֧��string

        Drink(){
        };
        Drink(string Name,double Price,string Brand,int Count,string Manufacturer):Commodity(Name, Price, Brand, Count, Manufacturer){
        }
        int getUuid() override;
        string getCategory() override;
};
const string Drink::category="����";
const int Drink::uuid=456;
int Drink::getUuid(){
    return uuid;
}
string Drink::getCategory(){
    return category;
}

//��ױƷ��
class Cosmetic:public Commodity
{
    public:
        static const string category;//���
        static const int uuid;//switch��֧��string

        Cosmetic(){
        };
        Cosmetic(string Name,double Price,string Brand,int Count,string Manufacturer):Commodity(Name, Price, Brand, Count, Manufacturer){
        }
        int getUuid() override;
        string getCategory() override;
};
const string Cosmetic::category="��ױƷ";
const int Cosmetic::uuid=789;
int Cosmetic::getUuid(){
    return uuid;
}
string Cosmetic::getCategory(){
    return category;
}

//����Ʒ��
class Daily:public Commodity
{
    public:
        static const string category;//���
        static const int uuid;//switch��֧��string

        Daily(){
        };
        Daily(string Name,double Price,string Brand,int Count,string Manufacturer):Commodity(Name, Price, Brand, Count, Manufacturer){
        }
        int getUuid() override;
        string getCategory() override;
};
const string Daily::category="����Ʒ";
const int Daily::uuid=101;
int Daily::getUuid(){
    return uuid;
}
string Daily::getCategory(){
    return category;
}