//
//Description����Ʒ����
//
#include <iostream>
using namespace std;
class Commodity {
private:
    string name;//����
    double price;//�۸�
    string brand;//Ʒ��
    int count;//���
    string manufacturer;//��������
    
        // static string category;//���
    public:
        //getter����:

        string getName();
        double getPrice();
        string getBrand();
        int getCount();
        string  getManufacturer();
        //setter����[���������]:

        void setName(string Name);
        void setPrice(double Price);
        void setBrand(string Brand);
        void setCount(int Count);
        void setManufacturer(string Manufacturer);
        //���캯��:

        Commodity();
        Commodity(string Name,double Price,string Brand,int Count,string Manufacturer);
        
        //��ӡ����
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

//setter����[���������]
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
    cout<<"��Ʒ���ࣺ"<<getCategory()<<"\n��Ʒ���ƣ�"<<name<<"\n��Ʒ�۸�"<<price<<"\n��ƷƷ�ƣ�"<<brand<<"\n���������"<<count<<"\n�������ң�"<<manufacturer<<endl;
};