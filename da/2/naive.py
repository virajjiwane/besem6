print('start')
import pandas as pd
df = pd.read_csv('dataset.csv')

import matplotlib.pyplot as plt
df.hist()

X = df.drop(['Outcome'],1)
Y = df['Outcome']

from sklearn import model_selection
X_train, X_test, Y_train, Y_test = model_selection.train_test_split(X, Y, test_size=0.2)

from sklearn.metrics import classification_report, accuracy_score, confusion_matrix
from sklearn.naive_bayes import GaussianNB
clf = GaussianNB()
clf.fit(X_train, Y_train)
predictions = clf.predict(X_test)

print(classification_report(Y_test, predictions))
print(accuracy_score(Y_test, predictions))

cm = confusion_matrix(Y_test, predictions)
print(cm)
labels = ['positive','negative']
fig = plt.figure()
ax = fig.add_subplot(111)
cax = ax.matshow(cm)
fig.colorbar(cax)
plt.title("Confusion matrix of naive bayes")
ax.set_xticklabels(['']+labels)
ax.set_yticklabels(['']+labels)
plt.xlabel('Predicted')
plt.ylabel('True')
plt.show()
