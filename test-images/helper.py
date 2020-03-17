#!/usr/bin/python3

#h_min = [9,9,9,5]
#h_max = [35,38,32,40]
#s_min = [108,108,105,75]
#s_max = [230,234,229,231]
#v_min = [89,147,94,80]
#v_max = [221,255,190,240]

#print(sum(h_min) // len(h_min),end = ' ');
#print(sum(h_max) // len(h_max),end = '\n');
#print(sum(s_min) // len(s_min),end = ' ');
#print(sum(s_max) // len(s_max),end = '\n');
#print(sum(v_min) // len(v_min),end = ' ');
#print(sum(v_max) // len(v_max),end = '\n');
import cv2
import numpy as np

#    h1 = int(input());
#    h2 = int(input());
#    s1 = int(input());
#    s2 = int(input());
#    v1 = int(input());
#    v2 = int(input());
h1 = 25;h2 = 32;
s1 = 255;s2 = 255;
v1 = 160;v2 = 255;

files = ["1.png","2.png","3.png","4.png","5.png","6.png","7.png"]

for x in files:

    img = cv2.imread(x)

    COLOR_MIN = np.array([h1, s1, v1])
    COLOR_MAX = np.array([h2, s2, v2])


    hsv_img = cv2.cvtColor(img,cv2.COLOR_BGR2HSV)

    frame_threshed = cv2.inRange(hsv_img, COLOR_MIN, COLOR_MAX)
    frame_threshed = cv2.GaussianBlur(frame_threshed,(7,7),0);
    im2 = frame_threshed.copy();

    contours,hierarchy = cv2.findContours(im2, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

    cv2.drawContours(img,contours,-1,(0,255,0), 3);

    cv2.imwrite(x + "_output.png", img)
    cv2.imwrite(x + "_mask.png", frame_threshed)

