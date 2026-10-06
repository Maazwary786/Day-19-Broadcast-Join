import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Day19App {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Day19-BroadcastJoin")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    val transactions = (1 to 20).map { i =>
      (i, i % 3 + 1, i * 100.0)
    }.toDF("transaction_id", "branch_id", "amount")

    val branches = Seq(
      (1, "Hyderabad"),
      (2, "Delhi"),
      (3, "Mumbai")
    ).toDF("branch_id", "branch_name")

    val result = transactions.join(
      broadcast(branches),
      Seq("branch_id"),
      "inner"
    )

    result.show()

    println("Execution Plan:")
    result.explain(true)

    spark.stop()
  }
}
