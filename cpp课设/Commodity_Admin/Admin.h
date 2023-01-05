//
//Description����Ʒ��Ϣ����
//
#include "kinds.h"
#include <fstream>
// #include <filesystem>

const int maxsize=100;//�����Ʒ����
const string filePath="info.txt";//�洢�ļ�λ��

class Admin{
    private://����
        Commodity *com[maxsize];
        int length;
    public:
        Admin();
        //�ļ���ȡ
        bool read();
        //�ļ�д��
        bool write();
        //�����Ʒ
        bool add(Commodity*c);
        //չʾ��Ʒ[true:չʾ�۸�false��չʾ���]
        void show(bool model);
        //ɾ��
        bool drop(int index);
        //�޸�[true:�޸ļ۸�false���޸Ŀ��]
        bool update(Commodity*c,int index,bool model);
        //����
        bool buy(int index,int num);
        //��ѯ[-1��ʾδ�ҵ�]
        int search(string name,int uuid);
        //ͨ��������ȡ
        Commodity* get(int index);
        //ͨ��name��ѯ
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
    //��ȡ����
    while (!fin.eof())
    {   uuid=0;//��ȡʱ���ǰ������ж��룬���鷳��ѡ��uuid�ж��Ƿ�Ϊ���С�
        fin>>name>>price>>brand>>count>>manufacturer>>uuid;
        switch (uuid)//���ˣ�switch��֧��string
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
        //length++ ���ˣ����ļ�Ҳ�ܶ�ȡ
        // cout<<"��ȡʱlength:"<<length<<endl;
    }
    fin.close();
    return true;  
}
bool Admin::add(Commodity*c){
    if(length<maxsize){
        com[length]=c;
        length++;
        // cout<<"���length��"<<length<<endl;
        return true;
    }else{
        cout<<"��Ʒ����������"<<endl;
        return false;
    }

}
void Admin::show(bool model){
    if(model){
        cout<<"���ƣ�"<<"      \t\t"<<"�۸�"<<endl;
    }else{
        cout<<"���ƣ�"<<"      \t\t"<<"��棺"<<endl;
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
    for(int i=index+1;i<length;i++){  //��Ҫɾ��λ�ú�������ǰ��
        com[i-1]=com[i];
    }
    length--;   //���ݸ�����һ
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
        cout<<"����ʧ�ܣ���治�㣡"<<endl;
        return false;
    }else{
        com[index]->setCount(com[index]->getCount()-num);
    }
    return true;
}
        
int Admin::search(string name,int uuid){
    int index=length-1;
    while(index>-1){
        if(com[index]->getName()==name&&com[index]->getUuid()==uuid){//�ж��Ƿ���������
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
        if(com[index]->getName()==name){//�ж��Ƿ���������
             com[index]->toString();
            cout<<"--------------------"<<endl;
            count++;
        }
        index--;
    }
   if(count==0){
        cout<<"δ��ѯ����ؼ�¼"<<endl;
    }
};
void Admin::printByUuid(int uuid){
 int index=length-1,count=0;
    cout<<endl;
    while(index>-1){
        if(com[index]->getUuid()==uuid){//�ж��Ƿ���������
             com[index]->toString();
             cout<<"--------------------"<<endl;
             count++;
        }
        index--;
    }
    if(count==0){
        cout<<"δ��ѯ����ؼ�¼"<<endl;
    }
}
void Admin::printByManufacturer(string manufacturer){
    int index=length-1,count=0;
    cout<<endl;
    while(index>-1){
        if(com[index]->getManufacturer()==manufacturer){//�ж��Ƿ���������
             com[index]->toString();
             cout<<"--------------------"<<endl;
             count++;
        }
        index--;
    }
    if(count==0){
        cout<<"δ��ѯ����ؼ�¼"<<endl;
    }
}
