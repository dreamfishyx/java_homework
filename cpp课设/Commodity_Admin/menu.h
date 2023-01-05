//
//Description:���彻������
//
#include "Admin.h"
Admin adm=Admin();

//ͳ��ģʽ
void show(){
    cout<<"��ѡ��ͳ��ģʽ��"<<endl;
    cout<<"  1.�Լ۸�ͳ��\n"<<"  2.�Կ��ͳ��"<<endl;
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
//���ģʽ
void add(){
    int kind=0,count=0;
    string name="",brand="",manufacturer="";
    double price=0;
    bool ans=true;
    cout<<"ѡ�������Ʒ���ࣺ\n"<<"1.ʳƷ 2.���� 3.��ױƷ 4.����Ʒ"<<endl;
    cout<<">>> ";
    cin>>kind;
    cout<<"��������Ʒ���ƣ�\n"<<">>> ";
    cin>>name;
    cout<<"��������Ʒ�۸�\n"<<">>> ";
    cin>>price;
    cout<<"��������Ʒ��棺\n"<<">>> ";
    cin>>count;
    cout<<"��������ƷƷ�ƣ�\n"<<">>> ";
    cin>>brand;
    cout<<"��������Ʒ���ң�\n"<<">>> ";
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
            cout<<"��ӳɹ���"<<endl;
}
//����ģʽ
void buy(){
    string name="";
    int uuid=0,sum=0,kind=0;
     cout<<"ѡ������Ʒ���ࣺ\n"<<"1.ʳƷ 2.���� 3.��ױƷ 4.����Ʒ"<<endl;
    cout<<">>> ";
    cin>>kind;
    cout<<"���빺����Ʒ���ƣ�\n"<<">>> ";
    cin>>name;
    switch (kind)//�ж�uuid
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
        cout<<"��Ǹ��δ�ҵ���Ҫ����Ʒ��"<<endl;
    }else{
        Commodity* c =adm.get(index);
        c->toString();
        cout<<"�����빺��������\n"<<">>> ";
        cin>>sum;
       if(adm.buy(index,sum)){
        cout<<"����ɹ���"<<endl;
       }
    }
}
//����
void search(){
    int kind=0,uuid=0;
    string name="",manufacturer="";
    cout<<"ѡ���ѯģʽ��\n"<<"  1.������\n"<<"  2.������\n"<<"  3.����������\n"<<">>> ";
    cin>>kind;
    switch (kind)
    {
    case 1:
        cout<<"ѡ��������Ʒ���ࣺ\n"<<"1.ʳƷ 2.���� 3.��ױƷ 4.����Ʒ"<<endl;
        cout<<">>> ";
        cin>>kind;
        switch (kind)//�ж�uuid
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
        cout<<"������Ʒ���ƣ�\n"<<">>> ";
        cin>>name;
        adm.printByName(name);
        break;
    case 3:
        cout<<"����������˾��\n"<<">>> ";
        cin>>manufacturer;
        adm.printByManufacturer(manufacturer);
        break;
    default:
        break;
    }
}
//ɾ��
void drop(){
    int kind=0,uuid=0;
    string name="";
     cout<<"ѡ��ɾ����Ʒ���ࣺ\n"<<"1.ʳƷ 2.���� 3.��ױƷ 4.����Ʒ"<<endl;
    cout<<">>> ";
    cin>>kind;
    cout<<"����ɾ����Ʒ���ƣ�\n"<<">>> ";
    cin>>name;
    switch (kind)//�ж�uuid
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
        cout<<"û���ҵ�Ҫɾ������Ʒ��"<<endl;
    }else{
        if(adm.drop(index))
            cout<<"ɾ���ɹ���"<<endl;
    }
}
//�޸�
void update(){
    int kind=0,count=0,uuid=0;
    string name="";
    Commodity* com;
    double price=0;
    cout<<"ѡ���޸���Ʒ���ࣺ\n"<<"1.ʳƷ 2.���� 3.��ױƷ 4.����Ʒ"<<endl;
    cout<<">>> ";
    cin>>kind;
    cout<<"������Ʒ���ƣ�\n"<<">>> ";
    cin>>name;
    switch (kind)//�ж�uuid
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
        cout<<"û���ҵ�Ҫ�޸ĵ���Ʒ��"<<endl;
    }else{
        cout<<"��ѡ���޸����ͣ�\n"<<"  1.�޸ļ۸�\n"<<"  2.�޸Ŀ��\n"<<">>> ";
        int i=0;
        cin>>i;
        switch (i)
        {
        case 1:
            cout<<"������۸�\n"<<">>> ";
            cin>>price;
            com->setPrice(price);
            if(adm.update(com,index,true))
                cout<<"�޸ĳɹ���"<<endl;
            break;
        default:
            cout<<"�������棺\n"<<">>> ";
            cin>>count;
            com->setCount(count);
            if(adm.update(com,index,false))
                cout<<"�޸ĳɹ���"<<endl;
            break;
        }
        
    }
}
//�˵�
void myMenu(){
cout<<"********************************************************"<<endl;
cout<<"\t--------��ӭ���볬����Ʒ����ϵͳ--------"<<endl;
cout<<"\t********\t1.������Ʒ\t********"<<endl;
cout<<"\t********\t2.�����Ʒ\t********"<<endl;
cout<<"\t********\t3.ɾ����Ʒ\t********"<<endl;
cout<<"\t********\t4.�޸���Ʒ\t********"<<endl;
cout<<"\t********\t5.��ѯ��Ʒ\t********"<<endl;
cout<<"\t********\t6.ͳ����Ʒ\t********"<<endl;
cout<<"\t********\t0.�˳�����\t********"<<endl;
cout<<"********************************************************"<<endl;
}
