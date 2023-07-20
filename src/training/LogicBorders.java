package training;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicBorders {



    //Берёт значения сверху
    public void findNearestUpContours(int posX, int posY) {
        try {
            System.load("D:\\testProject\\first\\simulator\\src\\opencv_java440.dll");

            Mat sourceImage = Imgcodecs.imread(GUI.pathArea);
            Mat windowBoard = Imgcodecs.imread(GUI.pathArea);

            if (sourceImage.empty()) {
                System.out.println("Не удалось загрузить изображение.");
            } else {
                //Обработка самого изображения
                Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_BGR2GRAY);
                Mat edges = new Mat();
                Imgproc.Canny(sourceImage, edges, 100, 200, 5);

                List<MatOfPoint> contours = new ArrayList<>();
                Mat hierarchy = new Mat();
                Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

                //Условия для фильтрации контуров, находящихся выше картинки робота
                int yThreshold = 36;
                List<MatOfPoint> nearestContours = new ArrayList<>();
                double minDistance = Double.MAX_VALUE;

                Point robotPosition = new Point(posX, posY);

                for (MatOfPoint contour : contours) {
                    Moments moments = Imgproc.moments(contour);
                    double centerX = moments.m10 / moments.m00;
                    double centerY = moments.m01 / moments.m00;

                    double distanceX = Math.abs(centerX - robotPosition.x);

                    if (distanceX <= yThreshold && centerY < posY) {
                        double distance = Math.sqrt(Math.pow(centerX - robotPosition.x, 2) + Math.pow(centerY - robotPosition.y, 2));
                        if (distance < minDistance) {
                            minDistance = distance;
                            nearestContours.clear();
                            nearestContours.add(contour);
                        }
                    }
                }
                // Отрисовка контуров подходящих по условию
                Scalar contourColor = new Scalar(0, 0, 255);
                Imgproc.drawContours(windowBoard, nearestContours, -1, contourColor, 1);

                //Вывод результата
                System.out.println("Минимальное расстояние до ближайшего контура: " + minDistance);
                HighGui.imshow("Ближайшие контуры", windowBoard);
                HighGui.waitKey(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Берёт значения снизу
    public void findNearestDownContours(int posX, int posY) {
        try {
            System.load("D:\\testProject\\first\\simulator\\src\\opencv_java440.dll");

            Mat sourceImage = Imgcodecs.imread(GUI.pathArea);
            Mat windowBoard = Imgcodecs.imread(GUI.pathArea);

            if (sourceImage.empty()) {
                System.out.println("Не удалось загрузить изображение.");
            } else {
                //Обработка самого изображения
                Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_BGR2GRAY);
                Mat edges = new Mat();
                Imgproc.Canny(sourceImage, edges, 100, 200, 5);

                List<MatOfPoint> contours = new ArrayList<>();
                Mat hierarchy = new Mat();
                Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

                //Условия для фильтрации контуров, находящихся ниже картинки робота
                int yThreshold = 36;
                List<MatOfPoint> nearestContours = new ArrayList<>();
                double minDistance = Double.MAX_VALUE;

                Point robotPosition = new Point(posX, posY);

                for (MatOfPoint contour : contours) {
                    Moments moments = Imgproc.moments(contour);
                    double centerX = moments.m10 / moments.m00;
                    double centerY = moments.m01 / moments.m00;

                    double distanceX = Math.abs(centerX - robotPosition.x);

                    if (distanceX <= yThreshold && centerY > posY) {
                        double distance = Math.sqrt(Math.pow(centerX - robotPosition.x, 2) + Math.pow(centerY - robotPosition.y, 2));
                        if (distance < minDistance) {
                            minDistance = distance;
                            nearestContours.clear();
                            nearestContours.add(contour);
                        }
                    }
                }

                //Отрисовка контуров подходящих по условию
                Scalar contourColor = new Scalar(0, 0, 255);
                Imgproc.drawContours(windowBoard, nearestContours, -1, contourColor, 1);

                //Вывод результата
                System.out.println("Минимальное расстояние до ближайшего контура: " + minDistance);
                HighGui.imshow("Ближайшие контуры", windowBoard);
                HighGui.waitKey(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //справа
    public void findNearestRightContours(int posX, int posY) {
        try {
            System.load("D:\\testProject\\first\\simulator\\src\\opencv_java440.dll");

            Mat sourceImage = Imgcodecs.imread(GUI.pathArea);
            Mat windowBoard = Imgcodecs.imread(GUI.pathArea);

            if (sourceImage.empty()) {
                System.out.println("Не удалось загрузить изображение.");
            } else {

                //Обработка самого изображения
                Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_BGR2GRAY);
                Mat edges = new Mat();
                Imgproc.Canny(sourceImage, edges, 100, 200, 5);

                List<MatOfPoint> contours = new ArrayList<>();
                Mat hierarchy = new Mat();
                Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

                //Условия для фильтрации контуров, находящихся ниже картинки робота
                List<MatOfPoint> nearestContours = new ArrayList<>();
                double minDistance = Double.MAX_VALUE;

                Point robotPosition = new Point(posX, posY);

                for (MatOfPoint contour : contours) {
                    Moments moments = Imgproc.moments(contour);
                    double centerX = moments.m10 / moments.m00;
                    double centerY = moments.m01 / moments.m00;

                    double distanceX = centerX - robotPosition.x;
                    double distanceY = Math.abs(centerY - robotPosition.y);

                    if (distanceX > 0 && distanceY <= 36) { // Проверка только справа и по вертикали
                        double distance = Math.sqrt(Math.pow(centerX - robotPosition.x, 2) + Math.pow(centerY - robotPosition.y, 2));
                        if (distance < minDistance) {
                            minDistance = distance;
                            nearestContours.add(contour);
                        }
                    }
                }

                //Отрисовка контуров подходящих по условию
                Scalar contourColor = new Scalar(0, 0, 255);
                Imgproc.drawContours(windowBoard, nearestContours, -1, contourColor, 1);

                //Вывод результата
                System.out.println("Минимальное расстояние до ближайшего контура: " + minDistance);
                HighGui.imshow("Ближайшие контуры", windowBoard);
                HighGui.waitKey(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //слева
    public void findNearestLeftContours(int posX, int posY) {
        try {
            System.load("D:\\testProject\\first\\simulator\\src\\opencv_java440.dll");

            Mat sourceImage = Imgcodecs.imread(GUI.pathArea);
            Mat windowBoard = Imgcodecs.imread(GUI.pathArea);

            if (sourceImage.empty()) {
                System.out.println("Не удалось загрузить изображение.");
            } else {
                //Обработка самого изображения

                Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_BGR2GRAY);
                Mat edges = new Mat();
                Imgproc.Canny(sourceImage, edges, 100, 200, 5);

                List<MatOfPoint> contours = new ArrayList<>();
                Mat hierarchy = new Mat();
                Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

                //Условия для фильтрации контуров, находящихся ниже картинки робота
                List<MatOfPoint> nearestContours = new ArrayList<>();
                double minDistance = Double.MAX_VALUE;

                Point robotPosition = new Point(posX, posY);

                for (MatOfPoint contour : contours) {
                    Moments moments = Imgproc.moments(contour);
                    double centerX = moments.m10 / moments.m00;
                    double centerY = moments.m01 / moments.m00;

                    double distanceX = centerX - robotPosition.x;
                    double distanceY = Math.abs(centerY - robotPosition.y);

                    if (distanceX < 0 && distanceY <= 36) { // Проверка только слева и по вертикали
                        double distance = Math.sqrt(Math.pow(centerX - robotPosition.x, 2) + Math.pow(centerY - robotPosition.y, 2));
                        if (distance < minDistance) {
                            minDistance = distance;
                            nearestContours.add(contour);
                        }
                    }
                }

                //Отрисовка контуров подходящих по условию
                Scalar contourColor = new Scalar(0, 0, 255);
                Imgproc.drawContours(windowBoard, nearestContours, -1, contourColor, 1);

                //Вывод результата
                System.out.println("Минимальное расстояние до ближайшего контура: " + minDistance);
                HighGui.imshow("Ближайшие контуры", windowBoard);
                HighGui.waitKey(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
