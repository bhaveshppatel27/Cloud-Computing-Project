from selenium import webdriver
from selenium.webdriver.common.keys import Keys
#from pyvirtualdisplay import Display
from selenium.webdriver.support.ui import WebDriverWait
import sys

def startSessions(Anum):
    count = int(Anum)
    #display = Display(visible=0, size=(800, 600))
    #display.start()
    browser = webdriver.Chrome('/Users/sumitdeshmukh/Downloads/chromedriver')
    url = 'https://www.northeasternbanking.com'
    browser.get(url)
    browser.implicitly_wait(3) # seconds
    assert 'Home' in browser.title
    user_list = ['hiram_16','theodore_8','fay_16','ryan_29','orla_9','wynne_8','garth_25','kitra_10','lilah_14','taylor_13','dante_23','sylvia_13','nyssa_7','carol_28','zoe_26','scott_28','jemima_1','emi_5','noah_3','fritz_21','armando_18','zeph_19','camilla_5','chase_14','graiden_14','noel_14','clare_26','guy_10','cameron_14','cameron_26','igor_22','kevyn_17','rashad_17','emi_1','dante_25','quinn_31','yoshi_26','keith_28','camilla_16','zeus_20','marcia_26','helen_15','camille_7','quin_16','piper_4','rooney_1','elmo_2','zahir_30','claudia_2','mallory_21','tasha_13','raphael_29','berk_29','kim_3','daria_24','allen_17','leroy_21','warren_15','dillon_2','shoshana_3','garrett_4','fiona_25','macey_3','hayden_30','troy_10','ulysses_22','summer_12','kathleen_2','jin_19','paul_9','macey_16','basil_12','pearl_22','quamar_21','minerva_4','imelda_24','aquila_23','ivana_5','davis_15','kato_9','anika_6','yetta_12','abdul_27','doris_21','price_28','dorothy_16','ann_10','hop_26','evan_16','debra_4']
    i = 0
    print(len(user_list))
    while(count > 0):
        try:
            button = browser.find_element_by_class_name('btn-primary')  # Find the search box
            button.click()

            browser.implicitly_wait(1) # seconds
            username_box = browser.find_element_by_name('userName')
            username_box.send_keys(user_list[i])
            

            password_box = browser.find_element_by_name('password')
            password_box.send_keys('Saurabh@31')

            signin_btn = browser.find_element_by_name('signin')
            signin_btn.click()

            browser.implicitly_wait(1) # seconds

            statement_toggle = browser.find_element_by_xpath('/html/body/div/div[2]/div[2]/div/div/div[1]/h4/a')
            statement_toggle.click()

            browser.implicitly_wait(1) # seconds

            submit_btn = browser.find_element_by_class_name('btn-warning')
            submit_btn.submit()
            print(i)
            i = i + 1
        except:
            print("failed for user ", user_list[i])
            i = i + 1
            #logout_btn = browser.find_element_by_class_name('btn-success')
            #logout_btn.submit()
        browser.execute_script("window.open('https://www.northeasternbanking.com','_blank');")
        browser.close()
        browser.switch_to.window(browser.window_handles[-1])
        count = count - 1


    browser.quit()
    #display.stop()

def main():
    # print command line arguments
    if len(sys.argv) != 2:
        print("Pass a number as argument to keep those many sessions alive")
    else:
        startSessions(sys.argv[1])

if __name__ == "__main__":
    main()
