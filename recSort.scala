package recSort
import scala.collection.breakOut
import scala.collection.mutable.Map
object recSort {
  
  def weightDistribute(len: Int):List[Double] ={
    val seq =(1 to len).toList
    seq.map(t => 1.0/t)
  }
  def recWeight(rec: List[String],weight: Double): Map[String, Double] ={
    var i = 0;
    val wei = weightDistribute(rec.length)
    var recWei = Map(rec(0) -> (weight*wei(0)))
    i = i + 1
    while( i < rec.length){
      recWei = recWei + (rec(i) -> (wei(i)*weight))
      i += 1
    }
    return recWei
  }
  def recomSort(recFun :String => List[String],items:List[String],
                weights:List[Double]) :List[String] = {
    val itemweight =  items zip weights
    var recPriority = Map("abcddd"->0.0)
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
    recPriority.remove("abcddd")
    val list = recPriority.toList
    val sortList = list.sortBy(_._2).reverse
    return sortList.map(t=>t._1)
  }
  def recomSort2(recFun :String => List[String],items:List[String],
                weightFun:Int=>List[Double]) :List[String] = {
    val itemweight =  items zip weightFun(items.length)
    var recPriority = Map("abcddd"->0.0)
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
    recPriority.remove("abcddd")
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