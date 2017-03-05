from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
import sys

def startSessions(Anum):
    count = int(Anum)
    browser = webdriver.Chrome('C:\\Users\\Bhavesh Patel\\Downloads\\chromedriver_win32\\chromedriver.exe') #Firefox('sPath to Firefox')
    browser.get('http://sample-env.4ctmvjty8r.us-west-2.elasticbeanstalk.com/')
    assert 'Home' in browser.title

    while(count > 0):
        button = browser.find_element_by_class_name('btn-primary')  # Find the search box
        button.click()

        browser.implicitly_wait(1) # seconds
        username_box = browser.find_element_by_name('userName')
        username_box.send_keys('ryan_29')

        password_box = browser.find_element_by_name('password')
        password_box.send_keys('Saurabh@31')

        signin_btn = browser.find_element_by_name('signin')
        signin_btn.click()

        statement_toggle = browser.find_element_by_xpath('/html/body/div/div[2]/div[2]/div/div/div[1]/h4/a')
        statement_toggle.click()

        browser.implicitly_wait(1) # seconds

        submit_btn = browser.find_element_by_class_name('btn-warning')
        submit_btn.submit()

        #logout_btn = browser.find_element_by_class_name('btn-success')
        #logout_btn.submit()
        browser.execute_script("window.open('http://sample-env.4ctmvjty8r.us-west-2.elasticbeanstalk.com/','_blank');")
        browser.switch_to.window(browser.window_handles[-1])
        count = count - 1

    browser.quit()

def main():
    # print command line arguments
    if len(sys.argv) != 2:
        print("Pass a number as argument to keep those many sessions alive")
    else:
        startSessions(sys.argv[1])

if __name__ == "__main__":
    main()