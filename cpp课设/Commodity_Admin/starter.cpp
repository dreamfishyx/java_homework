//main����
# include "menu.h"
using namespace std;
int main(){
    int choose=0;
    string ch="y";
    adm.read();//�����ļ�����
    while (true)
    {
       myMenu();
       cout<<"��ѡ����Ҫִ�еĲ�����[0-6]\n"<<">>> ";
       cin>>choose;
       switch (choose)
       {
       case 0:
            cout<<"ȷ���˳���?[y/n]\n"<<">>> ";
            cin>>ch;
            if(ch=="Y"||ch=="y"){
                adm.write();//��Ϣ���浽�ļ���
                exit(0);//�˳�����
            }else{
                system("pause");//���������
                system("CLS");//����
            }
            
            break;
       case 1:
            buy();
            system("pause");//���������
            system("CLS");//����
            break;
        case 2:
            add();
            system("pause");
            system("CLS");
            break;
        case 3:
            drop();
            system("pause");
            system("CLS");
            break;
        case 4:
            update();
            system("pause");
            system("CLS");
            break;
        case 5:
            search();
            system("pause");
            system("CLS");
            break;
        case 6:
            show();
            system("pause");
            system("CLS");
            break;
       default:
            system("pause");
            system("CLS");
            break;
       }
    }
    
    return 0;
}