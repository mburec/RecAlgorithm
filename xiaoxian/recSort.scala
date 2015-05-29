package recSort
import scala.collection.mutable.Map
object recSort {
  
  //weight distribution for top len items with normalization
  def weightDistribute(len: Int):List[Double] ={
    val seq =(1 to len).toList
    val weight = seq.map(t => 1.0/t)
    val sum = weight.reduceLeft(_ + _)
    weight.map(t=>t/sum)
  }
  //weight distribution for candidate recommendation items
  def recWeight(rec: List[String],weight: Double): scala.collection.immutable.Map[String, Double] ={
    var i = 0;
    val wei = weightDistribute(rec.length)
    val rewei = wei.map(t=>weight*t)
    (rec zip rewei).toMap
  }
  def recomSort(recFun :String => List[String],items:List[String],
                weights:List[Double]) :List[String] = {
    val itemweight =  items zip weights
    var recPriority:Map[String,Double] =  Map()
    for(iw <- itemweight){
      val rec = recFun(iw._1)
      var recpri = recWeight(rec,iw._2)
      for(key <- recpri.keySet){
        if(recPriority.contains(key)){
          recPriority(key) +=  recpri(key)
        }
        else{
          recPriority += (key -> recpri(key))
        }
      }
    }
    val list = recPriority.toList
    val sortList = list.sortBy(_._2).reverse
    return sortList.map(t=>t._1)
  }
  def recomSort2(recFun :String => List[String],items:List[String],
                weightFun:Int=>List[Double]) :List[String] = {
    val itemweight =  items zip weightFun(items.length)
    var recPriority:Map[String,Double] = Map()
    for(iw <- itemweight){
      val rec = recFun(iw._1)
      var recpri = recWeight(rec,iw._2)
      for(key <- recpri.keySet){
        if(recPriority.contains(key)){
          recPriority(key) +=  recpri(key)
        }
        else{
          recPriority += (key -> recpri(key))
        }
      }
    }
    val list = recPriority.toList
    val sortList = list.sortBy(_._2).reverse
    return sortList.map(t=>t._1)
  }
  def main(args: Array[String]){
    val items = (1 to 100).toList
    val itemsString = items.map(t=>t.toString())
    def rec(item:String):List[String]={
      val items = (1 to 10).toList
      items.map(t=>t.toString())
    }
    val broItem = List("4","5","8")
    print (recomSort2(rec,broItem,weightDistribute))
    
  }

}