# C++2022课设文档---梦鱼
## 1、问题描述：
 &ensp; &ensp;超市中包含四类商品：食品、化妆品、日用品和饮料。每类商品包含商品名称、价格、库存量 和生产厂家、品牌等信息。 现要求完成一个简单的商品管理系统，要求能对商品的销售、统计等进行管理。 

## 2、设计：
### (1)设计算法： 
#### A.读取数据：
  ![image](https://user-images.githubusercontent.com/91775733/209536185-a314dec2-56f8-4d1b-995f-0fc0132b72a6.png)

#### B.查找：
 ![image](https://user-images.githubusercontent.com/91775733/209536214-514fd785-62a9-4df0-a983-a91cdb6c567d.png)
 
#### C.购买：
  ![image](https://user-images.githubusercontent.com/91775733/209536252-e1af4a1a-867a-4214-973d-bab647bf29d5.png)

### (2)实现注释：
#### A.查找
```C++
int Admin::search(string name,int uuid){
    int index=length-1;//获取当前最后一条数据的索引
    while(index>-1){	//遍历数据
        if(com[index]->getName()==name&&com[index]->getUuid()==uuid)			
        {		//判断名称、类型是否满足条件
        	return index;//满足则返回索引
        }
        index--;//继续向前遍历
    }
    return index;//其实就是-1
}
```
#### B.删除
```C++
bool Admin::drop(int index){
    for(int i=index+1;i<length;i++){  //将要删除位置后面数据前移
        com[i-1]=com[i];//更新数据
    }
    length--;   //数据总数-1
    return true;	//返回处理成功
}
```
#### C.添加
``` C++
bool Admin::add(Commodity*c){
    if(length<maxsize){	//判断是否超过数据最大个数
        com[length]=c;	//未超过则添加
        length++;	//数据个数+1
        return true;//返回true
    }else{
        cout<<"商品类型已满！"<<endl;	//超过，则不添加，返回false
        return false;
    }
}
```
### (3)调试报告：
1. 在对文件进行读取时，总是会将最后一行空白进行读入，造成有一行乱码数据。因而在读取到数据后进行判断，若读取到的uuid不属于任何一个商品类，则不予操作。
2. 在刚开始进行文件保存后，一个string变量就把所有数据读出了，导致其他变量均无值。因而采用每行一个商品数据，同一行不同成员变量之间用空格分隔。
3. c++switch不支持对string类型的匹配，因而在每个类中设置一个uuid，用于按需创建不同类型对象。但是由于uuid是子类独有，即必须在每个子类提个静态uuid变量。同时为了得到uuid，在父类设置getUuid的虚函数，并在子类中各自实现它。

## 3、附录：
### （1）菜单界面：
![image](https://user-images.githubusercontent.com/91775733/209535665-f1111645-bede-44b7-ae7b-551795ba6a8d.png)
### （2）购买界面：
![image](https://user-images.githubusercontent.com/91775733/209535686-e48910a4-69f0-445c-aa66-5121b331fd62.png)
### （3）添加界面：
![image](https://user-images.githubusercontent.com/91775733/209535802-b3640fcb-97da-4424-9eca-56285964a36f.png)
### （4）删除界面：
![image](https://user-images.githubusercontent.com/91775733/209535855-f09e7ac5-47f9-4bb1-ac1d-5d1ac12cbb81.png)
### （5）修改界面：
![image](https://user-images.githubusercontent.com/91775733/209535954-807ba8e2-3fb2-448c-9045-fa3666543035.png)
![image](https://user-images.githubusercontent.com/91775733/209535938-117223dd-b1e3-4f58-b51a-14fd6853ad58.png)
### （6）查询界面：
![image](https://user-images.githubusercontent.com/91775733/209536039-5b43c387-97cf-4a6a-9fe5-77d350780483.png)
![image](https://user-images.githubusercontent.com/91775733/209536050-1888a5d8-889d-438f-82b8-b2f9e0795a7c.png)
![image](https://user-images.githubusercontent.com/91775733/209536061-d47e3901-9a3a-465a-b345-265cd4f3c292.png)
### （7）统计界面：
![image](https://user-images.githubusercontent.com/91775733/209536080-6756f95a-024b-4207-b792-281fa73b6f86.png)
![image](https://user-images.githubusercontent.com/91775733/209536095-f515dffe-5a1d-46fa-9e6f-5dd497153380.png)
### （8）退出保存：
![image](https://user-images.githubusercontent.com/91775733/209536118-4536b9a4-4d9f-4050-b245-fa34f90032da.png)

