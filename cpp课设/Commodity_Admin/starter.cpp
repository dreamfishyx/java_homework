//main方法
# include "menu.h"
using namespace std;
int main(){
    int choose=0;
    string ch="y";
    adm.read();//加载文件数据
    while (true)
    {
       myMenu();
       cout<<"请选择您要执行的操作：[0-6]\n"<<">>> ";
       cin>>choose;
       switch (choose)
       {
       case 0:
            cout<<"确认退出吗?[y/n]\n"<<">>> ";
            cin>>ch;
            if(ch=="Y"||ch=="y"){
                adm.write();//信息保存到文件中
                exit(0);//退出程序
            }else{
                system("pause");//任意键继续
                system("CLS");//清屏
            }
            
            break;
       case 1:
            buy();
            system("pause");//任意键继续
            system("CLS");//清屏
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