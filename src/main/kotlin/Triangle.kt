import kotlin.math.sqrt
import kotlin.math.pow

class Triangle(private val point1: Point, private val point2: Point, private val point3: Point) {

    private fun averageDot(pointA: Point, pointB: Point): Point {
        val x = (pointA.x + pointB.x) / 2
        val y = (pointA.y + pointB.y) /2
        return Point(x, y)
    }

    private fun cornerOfSide(pointA: Point, pointB: Point): Double {
        return (pointB.y - pointA.y) / (pointB.x - pointA.x)
    }

    private fun cornerOfBisect(x: Double): Double {
        return if(x == 0.0) {
            0.0
        } else {
            -1 / x
        }
    }

    fun findCenter(): Point {
        val point1 = this.averageDot(this.point1, this.point2)
        val point2 = this.averageDot(this.point1, this.point3)
        val corner1 = this.cornerOfBisect(this.cornerOfSide(this.point1, this.point2))
        val corner2 = this.cornerOfBisect(this.cornerOfSide(this.point1, this.point3))

        val x = (corner1 * point1.x - point1.y + point2.y - corner2 * point2.x) / (corner1 - corner2)
        val y = corner1 * x - corner1 * point1.x + point1.y
        return Point(x, y)
    }

    fun findRadius(center: Point): Double {
        val dot = this.averageDot(this.point1, this.point2)
        return sqrt((dot.x - center.x).pow(2.0) + (dot.y - center.y).pow(2.0))
    }
}